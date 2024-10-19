package com.example.mvvmflowstateflow.favorite.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmflowstateflow.R
import com.example.mvvmflowstateflow.database.AppDataBase
import com.example.mvvmflowstateflow.database.FavProductsDAO
import com.example.mvvmflowstateflow.database.ProductsLocalDataSourceImpl
import com.example.mvvmflowstateflow.favorite.viewmodel.FavViewModel
import com.example.mvvmflowstateflow.favorite.viewmodel.FavViewModelFactory
import com.example.mvvmflowstateflow.model.Product
import com.example.mvvmflowstateflow.model.ProductsRepositoryImpl
import com.example.mvvmflowstateflow.network.ProductsRemoteDataSourceImpl
import kotlinx.coroutines.launch

/**
 * Activity for displaying the list of favorite products.
 * It sets up the RecyclerView and the ViewModel for managing favorite products.
 */
class FavActivity : AppCompatActivity() {

    private lateinit var favViewModel: FavViewModel
    private lateinit var favListAdapter: FavListAdapter
    private lateinit var favRecyclerView: RecyclerView

    private lateinit var localDataSource: ProductsLocalDataSourceImpl
    private lateinit var remoteDataSource: ProductsRemoteDataSourceImpl
    private lateinit var repo: ProductsRepositoryImpl

    /**
     * Called when the activity is starting. This is where most initialization should go.
     * Sets up the RecyclerView, ViewModel, and data sources, and starts observing favorite products.
     *
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down, this contains the saved data.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fav)

        // Initialize RecyclerView
        favRecyclerView = findViewById(R.id.fav_myRecyclerView)
        favRecyclerView.layoutManager = LinearLayoutManager(this)

        // Set up the adapter and pass the delete callback method
        favListAdapter = FavListAdapter { product -> deleteProduct(product) }
        favRecyclerView.adapter = favListAdapter

        // Initialize the Room database and DAO
        val database = AppDataBase.getDatabase(this)
        val dao: FavProductsDAO = database.favProductsDao()

        // Set up local and remote data sources and repository
        localDataSource = ProductsLocalDataSourceImpl(dao)
        remoteDataSource = ProductsRemoteDataSourceImpl()
        repo = ProductsRepositoryImpl(localDataSource, remoteDataSource)

        // Initialize ViewModel using the factory
        val factory = FavViewModelFactory(repo)
        favViewModel = viewModels<FavViewModel> { factory }.value

        // Observe favorite products from the ViewModel and update the RecyclerView
        lifecycleScope.launch {
            favViewModel.favoriteProducts.collect { products ->
                favListAdapter.submitList(products)
            }
        }
    }

    /**
     * Deletes a product from the favorites list by calling the ViewModel's delete function.
     *
     * @param product The [Product] to be removed from the favorites list.
     */
    private fun deleteProduct(product: Product) {
        favViewModel.deleteProductFromFavorites(product)
    }
}
