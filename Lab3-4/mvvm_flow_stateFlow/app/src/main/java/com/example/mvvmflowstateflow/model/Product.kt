package com.example.mvvmflowstateflow.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product_table")
data class Product(
    @PrimaryKey val id: Int,
    val price: Double,
    val thumbnail: String?,
    val title: String,
    val brand:String,
    val description: String

)
