package com.example.mvvmflowstateflow.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Singleton object that provides a Retrofit instance for network operations.
 *
 * This object encapsulates the configuration of the Retrofit instance, including
 * the base URL and the converter factory for JSON parsing.
 */
object RetrofitHelper {

    // Base URL for the API
    private const val BASE_URL = "https://dummyjson.com/"

    /**
     * Returns a configured instance of Retrofit.
     *
     * This function initializes and configures the Retrofit instance with
     * the specified base URL and adds a Gson converter for JSON serialization
     * and deserialization.
     *
     * @return A [Retrofit] instance ready for making network calls.
     */
    fun getInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
