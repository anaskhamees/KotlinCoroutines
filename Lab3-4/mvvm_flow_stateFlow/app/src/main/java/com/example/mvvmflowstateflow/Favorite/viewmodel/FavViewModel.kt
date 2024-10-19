package com.example.mvvmflowstateflow.favorite.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmflowstateflow.model.Product
import com.example.mvvmflowstateflow.model.ProductsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

/**
 * ViewModel for managing the favorite products in the application.
 * It provides the data to the UI and acts as a communication center between the repository and the UI.
 *
 * @param repository The [ProductsRepository] used for accessing product data.
 */
class FavViewModel(private val repository: ProductsRepository) : ViewModel() {

    /**
     * A Flow that provides a list of favorite products.
     * This can be observed by the UI to update the display when data changes.
     */
    val favoriteProducts: Flow<List<Product>> = repository.getFavoriteProducts()

    /**
     * Deletes a product from the favorites list.
     * This method launches a coroutine in the ViewModel's scope to perform the deletion
     * without blocking the UI thread.
     *
     * @param product The [Product] to be removed from the favorites list.
     */
    fun deleteProductFromFavorites(product: Product) {
        viewModelScope.launch {
            repository.removeProductFromFavorites(product)
        }
    }
}
