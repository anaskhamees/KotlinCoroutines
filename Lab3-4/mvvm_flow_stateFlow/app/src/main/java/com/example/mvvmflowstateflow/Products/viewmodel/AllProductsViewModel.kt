package com.example.mvvmflowstateflow.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmflowstateflow.model.Product
import com.example.mvvmflowstateflow.model.ProductsRepository
import com.example.mvvmflowstateflow.network.APIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

/**
 * ViewModel for managing UI-related data for the All Products screen.
 *
 * This ViewModel fetches product data from a repository and provides it to the UI.
 * It also manages the loading state and favorite actions for products.
 *
 * @property repository The repository that provides product data.
 */
class AllProductsViewModel(private val repository: ProductsRepository) : ViewModel() {

    private val _products = MutableLiveData<List<Product>>()
    val products: LiveData<List<Product>> get() = _products

    val _stateFlow = MutableStateFlow<APIState>(APIState.OnLoading())
    val stateFlow: StateFlow<APIState> = _stateFlow

    /**
     * Fetches products from the network and updates the LiveData and StateFlow.
     *
     * This method is called to initiate a network request to retrieve product data.
     * It handles loading and failure states appropriately.
     */
    fun fetchProductsFromNetwork() {
        viewModelScope.launch {

            repository.getAllProducts()
                .catch { e ->
                    _stateFlow.value = APIState.OnFailure(e)
                }
                .collect {
                    _products.postValue(it)
                    _stateFlow.value = APIState.OnSuccess(it)
                }
        }
    }

    /**
     * Adds a product to the favorites.
     *
     * This method is called to add a specified product to the user's favorites.
     *
     * @param product The product to be added to favorites.
     */
    fun addProductToFavorites(product: Product) {
        viewModelScope.launch {
            repository.addProductToFavorites(product)
        }
    }
}
