package com.canitopai.ud6_recu_app.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.canitopai.ud6_recu_app.data.model.Product

@Database(entities = [ProductModel::class], version = 1)
abstract class ProductDb: RoomDatabase() {
   abstract fun productDao(): ProductDAO
}