package com.example.mvvmflowstateflow.favorite.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmflowstateflow.model.ProductsRepository

/**
 * A factory class for creating instances of [FavoriteViewModel].
 * This is necessary to pass the required [ProductsRepository] to the ViewModel.
 *
 * @param repository The [ProductsRepository] instance to be used by the ViewModel.
 */
class FavoriteViewModelFactory(private val repository: ProductsRepository) : ViewModelProvider.Factory {

    /**
     * Creates an instance of the specified ViewModel class.
     *
     * @param modelClass The class of the ViewModel to create.
     * @return An instance of the specified ViewModel class.
     * @throws IllegalArgumentException If the specified ViewModel class is unknown or cannot be created.
     */
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FavoriteViewModel::class.java)) {
            return FavoriteViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
