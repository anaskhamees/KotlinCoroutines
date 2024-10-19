package com.example.mvvmflowstateflow.network

import com.example.mvvmflowstateflow.Network.ProductService
import com.example.mvvmflowstateflow.Network.ProductsRemoteDataSource
import com.example.mvvmflowstateflow.model.ProductsResponse
import retrofit2.HttpException

/**
 * Implementation of the ProductsRemoteDataSource interface.
 *
 * This class is responsible for fetching product data from a remote API using Retrofit.
 *
 * @constructor Creates an instance of ProductsRemoteDataSourceImpl.
 */
class ProductsRemoteDataSourceImpl : ProductsRemoteDataSource {

    // Instance of the ProductService to make API calls
    private val productService: ProductService = RetrofitHelper.getInstance().create(ProductService::class.java)

    /**
     * Fetches product data from the remote API.
     *
     * This suspending function makes a network call to retrieve a list of products.
     * If the network call fails, an exception is thrown.
     *
     * @return A [ProductsResponse] object containing the list of products retrieved from the API.
     * @throws Exception if the network call fails.
     */
    override suspend fun fetchProducts(): ProductsResponse {
        try {
            // Make the API Retrofit call
            return productService.getProducts()
        } catch (e: HttpException) {
            throw Exception("Network call failed")
        }
    }
}
