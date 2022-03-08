package com.canitopai.ud6_recu_app.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Product")
data class ProductModel(
    @ColumnInfo(name = "available")
    val available: Boolean,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "discountPrice")
    val discountPrice: Double,
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    @ColumnInfo(name = "imageUrl")
    val imageUrl: String,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "regularPrice")
    val regularPrice: Double,
    @ColumnInfo(name = "stock")
    val stock: Int
)