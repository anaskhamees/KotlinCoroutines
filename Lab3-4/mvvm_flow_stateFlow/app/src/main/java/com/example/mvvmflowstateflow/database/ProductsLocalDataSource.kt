package com.example.mvvmflowstateflow.database

import com.example.mvvmflowstateflow.model.Product
import kotlinx.coroutines.flow.Flow

/**
 * Local data source interface for handling product operations.
 * This interface abstracts database operations for adding, removing, and fetching products.
 * It is part of the repository pattern, providing a clean API for data access.
 *
 * @constructor This is an interface defining methods for managing local product data.
 */
interface ProductsLocalDataSource {

    /**
     * Adds a product to the local database.
     *
     * @param product The product to be added.
     */
    suspend fun addProduct(product: Product)

    /**
     * Removes a product from the local database.
     *
     * @param product The product to be removed.
     */
    suspend fun removeProduct(product: Product)

    /**
     * Retrieves all favorite products from the local database.
     * This method returns a [Flow] that emits the list of products asynchronously.
     *
     * @return A [Flow] containing the list of favorite products.
     */
    fun getFavoriteProducts(): Flow<List<Product>>

    /**
     * Retrieves a specific product by its ID from the local database.
     * If no product is found with the provided ID, this method returns `null`.
     *
     * @param id The ID of the product to retrieve.
     * @return The product with the given ID, or `null` if not found.
     */
    //suspend fun getProductById(id: Int): Product?
}
