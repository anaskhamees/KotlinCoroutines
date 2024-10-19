package com.example.mvvmflowstateflow.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmflowstateflow.model.ProductsRepository

/**
 * Factory class for creating instances of [AllProductsViewModel].
 *
 * This factory takes a [ProductsRepository] as a parameter to provide it to the ViewModel.
 *
 * @property repository The repository instance used by the ViewModel.
 */
class AllProductsViewModelFactory(private val repository: ProductsRepository) : ViewModelProvider.Factory {

    /**
     * Creates a new instance of [ViewModel].
     *
     * This method checks if the requested ViewModel class is [AllProductsViewModel],
     * and if so, creates and returns it with the provided repository. Otherwise, it throws
     * an [IllegalArgumentException].
     *
     * @param modelClass The class of the ViewModel to create.
     * @return An instance of [ViewModel] of type [T].
     * @throws IllegalArgumentException If the provided [modelClass] is not assignable from [AllProductsViewModel].
     */
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AllProductsViewModel::class.java)) {
            return AllProductsViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
