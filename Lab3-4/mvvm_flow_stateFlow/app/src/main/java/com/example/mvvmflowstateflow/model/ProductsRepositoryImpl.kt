package com.example.mvvmflowstateflow.model

import android.util.Log
import com.example.mvvmflowstateflow.Network.ProductsRemoteDataSource
import com.example.mvvmflowstateflow.database.ProductsLocalDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Implementation of the ProductsRepository interface.
 *
 * This class is responsible for managing product data from local and remote data sources.
 *
 * @property localDataSource The local data source for managing favorite products.
 * @property remoteDataSource The remote data source for fetching product data from a network.
 *
 * @constructor Creates an instance of ProductsRepositoryImpl with the specified local and remote data sources.
 *
 * @param localDataSource The local data source to be used for accessing stored favorite products.
 * @param remoteDataSource The remote data source to be used for fetching products from the network.
 */
class ProductsRepositoryImpl(
    private val localDataSource: ProductsLocalDataSource,
    private val remoteDataSource: ProductsRemoteDataSource
) : ProductsRepository {

    /**
     * Fetches all products from the remote data source.
     *
     * This function retrieves product data from a network source and emits the list of products.
     * It is a suspending function that runs in a coroutine context.
     *
     * @return A [Flow] that emits a list of [Product] objects fetched from the remote data source.
     */
    override suspend fun getAllProducts(): Flow<List<Product>> = flow {
        val result = remoteDataSource.fetchProducts()
        emit(result.products)
    }

    /**
     * Retrieves favorite products from the local database.
     *
     * This function accesses the local data source to get the list of favorite products.
     *
     * @return A [Flow] that emits a list of favorite [Product] objects from the local database.
     */
    override fun getFavoriteProducts(): Flow<List<Product>> {
        return localDataSource.getFavoriteProducts()
    }

    /**
     * Adds a product to the local favorites.
     *
     * This function stores a product in the local database as a favorite.
     *
     * @param product The [Product] object to be added to the favorites.
     */
    override suspend fun addProductToFavorites(product: Product) {
        localDataSource.addProduct(product)
    }

    /**
     * Removes a product from the local favorites.
     *
     * This function deletes a specified product from the local database favorites.
     *
     * @param product The [Product] object to be removed from the favorites.
     */
    override suspend fun removeProductFromFavorites(product: Product) {
        localDataSource.removeProduct(product)
    }
}
