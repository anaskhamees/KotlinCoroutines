package com.example.mvvmflowstateflow.Network

import com.example.mvvmflowstateflow.model.ProductsResponse

interface ProductsRemoteDataSource {
    suspend fun fetchProducts(): ProductsResponse
}
