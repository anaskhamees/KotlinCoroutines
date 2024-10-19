package com.example.mvvmflowstateflow.Products.View

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmflowstateflow.R
import com.example.mvvmflowstateflow.View.AllProductAdapter
import com.example.mvvmflowstateflow.database.AppDataBase
import com.example.mvvmflowstateflow.database.ProductsLocalDataSourceImpl
import com.example.mvvmflowstateflow.network.ProductsRemoteDataSourceImpl
import com.example.mvvmflowstateflow.model.ProductsRepositoryImpl
import com.example.mvvmflowstateflow.database.FavProductsDAO
import com.example.mvvmflowstateflow.network.APIState
import com.example.mvvmflowstateflow.viewmodel.AllProductsViewModel
import com.example.mvvmflowstateflow.viewmodel.AllProductsViewModelFactory
import kotlinx.coroutines.launch

/**
 * Activity class that displays a list of products and allows users to add them to favorites.
 *
 * This activity retrieves products from a remote data source and displays them in a RecyclerView.
 * It also provides a visual state indicator for loading and error states, and allows users to
 * add products to their favorites.
 */
class AllProductActivity : AppCompatActivity() {

    private lateinit var localDataSource: ProductsLocalDataSourceImpl
    private lateinit var remoteDataSource: ProductsRemoteDataSourceImpl
    private lateinit var repository: ProductsRepositoryImpl

    lateinit var imageViewStates: ImageView

    // ViewModel instance to manage UI-related data in a lifecycle-conscious way
    private val viewModel: AllProductsViewModel by viewModels {
        AllProductsViewModelFactory(repository)
    }

    private lateinit var allProductAdapter: AllProductAdapter

    /**
     * Called when the activity is starting. This is where most initialization should go.
     *
     * Initializes the Room database, DAO, data sources, RecyclerView, and observes product state.
     *
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down
     *                           then this Bundle contains the data it most recently supplied in
     *                           onSaveInstanceState(Bundle). Otherwise it is null.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products)

        // Initialize Database and DAO
        val database = AppDataBase.getDatabase(this) // Get your Room database instance
        val dao: FavProductsDAO = database.favProductsDao() // get the DAO

        // Initialize data sources
        localDataSource = ProductsLocalDataSourceImpl(dao) // Pass the DAO to your local data source
        remoteDataSource = ProductsRemoteDataSourceImpl() // Initialize your remote data source
        repository = ProductsRepositoryImpl(localDataSource, remoteDataSource) // Initialize repository

        // Initialize RecyclerView
        val recyclerView: RecyclerView = findViewById(R.id.recyclerViewProducts)
        imageViewStates = findViewById(R.id.imageViewStates)

        recyclerView.layoutManager = LinearLayoutManager(this)

        // Initialize Adapter
        allProductAdapter = AllProductAdapter()
        recyclerView.adapter = allProductAdapter

        // Observe product list from ViewModel
        viewModel.fetchProductsFromNetwork()

        lifecycleScope.launch {
            viewModel.stateFlow.collect { state ->
                when (state) {
                    is APIState.OnLoading -> {
                        imageViewStates.visibility = View.VISIBLE
                        imageViewStates.setImageDrawable(ContextCompat.getDrawable(this@AllProductActivity, R.drawable.loading))
                    }

                    is APIState.OnSuccess -> {
                        imageViewStates.visibility = View.GONE
                        allProductAdapter.setProducts(state.data)
                    }

                    is APIState.OnFailure -> {
                        imageViewStates.visibility = View.VISIBLE
                        imageViewStates.setImageDrawable(ContextCompat.getDrawable(this@AllProductActivity, R.drawable.noconnection))
                    }
                }
            }
        }

        // Set up favorite button click listener
        allProductAdapter.setOnFavoriteClickListener { product ->
            viewModel.addProductToFavorites(product)
            Toast.makeText(this, "Product Added to Favorites", Toast.LENGTH_SHORT).show()
        }
    }
}
