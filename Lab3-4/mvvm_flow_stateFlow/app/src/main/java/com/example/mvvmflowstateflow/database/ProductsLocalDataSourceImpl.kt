package com.example.mvvmflowstateflow.database

import com.example.mvvmflowstateflow.model.Product
import kotlinx.coroutines.flow.Flow

/**
 * Implementation of the [ProductsLocalDataSource] interface, responsible for managing
 * product-related operations in the local database.
 *
 * This class uses the [FavProductsDAO] to interact with the database and performs actions such as
 * adding, removing, and retrieving favorite products.
 *
 * @property dao The Data Access Object (DAO) used to access the database.
 */
class ProductsLocalDataSourceImpl(
    private val dao: FavProductsDAO
) : ProductsLocalDataSource {

    /**
     * Adds a product to the local database by invoking the insert method of the DAO.
     * The product will be replaced if it already exists.
     *
     * @param product The product to be added to the favorites list.
     */
    override suspend fun addProduct(product: Product) {
        dao.insertProduct(product)
    }

    /**
     * Removes a product from the local database by invoking the delete method of the DAO.
     *
     * @param product The product to be removed from the favorites list.
     */
    override suspend fun removeProduct(product: Product) {
        dao.deleteProduct(product)
    }

    /**
     * Retrieves all favorite products from the local database. This returns a [Flow]
     * that emits updates to the list of products asynchronously.
     *
     * @return A [Flow] containing the list of all favorite products.
     */
    override fun getFavoriteProducts(): Flow<List<Product>> {
        return dao.getAllFavProducts()
    }

    // Commented out method for retrieving product by ID
    // /**
    //  * Retrieves a product from the database based on its ID.
    //  * This method is commented out and can be used when necessary.
    //  *
    //  * @param id The ID of the product to retrieve.
    //  * @return The product with the specified ID, or `null` if not found.
    //  */
    // override suspend fun getProductById(id: Int): Product? {
    //     return dao.getProductById(id)
    // }
}
