package com.example.mvvmflowstateflow.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mvvmflowstateflow.model.Product

/**
 * AppDataBase class representing the Room database for the application.
 * This database holds the favorite products and provides a DAO to access the data.
 *
 * @constructor This is an abstract class extending RoomDatabase.
 */
@Database(entities = [Product::class], version = 1, exportSchema = false)
abstract class AppDataBase : RoomDatabase() {

    /**
     * Provides the DAO (Data Access Object) for interacting with favorite products.
     *
     * @return The DAO for favorite products [FavProductsDAO].
     */
    abstract fun favProductsDao(): FavProductsDAO

    /**
     * Companion object containing the singleton pattern for the database instance.
     * Ensures that only one instance of the database is created throughout the application's lifecycle.
     */
    companion object {

        /**
         * A nullable reference to the database instance.
         * This will hold the singleton instance of the database.
         */
        private var INSTANCE: AppDataBase? = null

        /**
         * Returns the singleton instance of the database.
         *
         * This method creates a new database instance if one doesn't already exist, ensuring thread safety
         * using the synchronized block. It uses the application context to avoid memory leaks.
         *
         * @param context The context used to create or retrieve the database.
         * @return The singleton [AppDataBase] instance.
         */
        fun getDatabase(context: Context): AppDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDataBase::class.java,
                    "products_database"
                ).build()

                // Assign the new instance to the INSTANCE variable
                INSTANCE = instance

                // Return the created instance
                instance
            }
        }
    }
}
