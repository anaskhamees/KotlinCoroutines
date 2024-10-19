package com.example.mvvmflowstateflow.model

import kotlinx.coroutines.flow.Flow

/**
 * Interface for managing product data and operations related to products.
 * This repository pattern allows for separation of concerns and easier testing.
 */
interface ProductsRepository {

    /**
     * Retrieves all products as a flow of a list.
     *
     * @return A [Flow] that emits a list of [Product] items.
     */
    suspend fun getAllProducts(): Flow<List<Product>>

    /**
     * Retrieves all favorite products as a flow of a list.
     *
     * @return A [Flow] that emits a list of favorite [Product] items.
     */
    fun getFavoriteProducts(): Flow<List<Product>>

    /**
     * Adds a product to the favorites list.
     *
     * @param product The [Product] to be added to favorites.
     */
    suspend fun addProductToFavorites(product: Product)

    /**
     * Removes a product from the favorites list.
     *
     * @param product The [Product] to be removed from favorites.
     */
    suspend fun removeProductFromFavorites(product: Product)
}
