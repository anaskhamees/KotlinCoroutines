package com.example.mvvmflowstateflow.database

import androidx.room.*
import com.example.mvvmflowstateflow.model.Product
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object (DAO) for interacting with the product table in the database.
 * This interface provides methods for inserting, deleting, and querying favorite products.
 *
 * @constructor This is an interface defining Room database operations.
 */
@Dao
interface FavProductsDAO {

    /**
     * Inserts a product into the product table.
     * If there is a conflict (e.g., the product already exists), it will replace the existing entry.
     *
     * @param product The product to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(product: Product)

    /**
     * Deletes a product from the product table.
     *
     * @param product The product to be deleted.
     */
    @Delete
    suspend fun deleteProduct(product: Product)

    /**
     * Retrieves all favorite products from the product table.
     * This method returns a Flow object, enabling asynchronous and reactive programming.
     *
     * @return A [Flow] containing a list of all favorite products.
     */
    @Query("SELECT * FROM product_table")
    fun getAllFavProducts(): Flow<List<Product>>

    /**
     * Retrieves a specific product by its ID from the product table.
     * If no product is found with the provided ID, this method returns `null`.
     *
     * @param id The ID of the product to retrieve.
     * @return The product with the given ID, or `null` if not found.
     */
//    @Query("SELECT * FROM product_table WHERE id = :id LIMIT 1")
//    suspend fun getProductById(id: Int): Product?
}
