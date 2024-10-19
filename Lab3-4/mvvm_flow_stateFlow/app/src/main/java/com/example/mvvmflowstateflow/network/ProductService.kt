package com.example.mvvmflowstateflow.Network

import com.example.mvvmflowstateflow.model.ProductsResponse
import retrofit2.http.GET

interface ProductService {

    @GET("products")
    suspend fun getProducts(): ProductsResponse
}
