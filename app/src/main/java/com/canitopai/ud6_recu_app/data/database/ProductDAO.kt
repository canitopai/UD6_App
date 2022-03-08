package com.canitopai.ud6_recu_app.data.database

import androidx.room.*
import com.canitopai.ud6_recu_app.data.model.Product
import com.canitopai.ud6_recu_app.data.model.ProductItem

@Dao
interface ProductDAO {
 @Query("SELECT * FROM Product ORDER BY Product.name")
 fun findProducts(): MutableList<ProductItem>
 @Query("SELECT * FROM Product WHERE Product.id = :productId LIMIT 1")
 fun findProductById(productId: Int):ProductModel
 @Insert(onConflict = OnConflictStrategy.REPLACE)
 fun add(product: ProductModel)
 @Delete
 fun delete(product: ProductModel)
 @Query("DELETE FROM Product WHERE Product.name = :productName")
 fun deleteProd(productName: String)
 //@Query("SELECT COUNT(id) FROM Product")
 //fun checkIfEmpty()
}