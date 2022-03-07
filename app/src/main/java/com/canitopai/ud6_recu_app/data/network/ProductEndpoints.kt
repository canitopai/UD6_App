package com.canitopai.ud6_recu_app.data.network

import com.canitopai.ud6_recu_app.data.model.Product
import com.canitopai.ud6_recu_app.data.model.ProductItem
import retrofit2.Call
import retrofit2.http.*

interface ProductEndpoints {
    @GET("/product/")
    fun getProducts(): Call<List<ProductItem>>

    @GET("/product/{id}")
    fun getProductDetailed(@Path("id")id: String): Call<ProductItem>?

    @POST("/product/")
    fun savePost(@Body post: ProductItem): Call<ProductItem>

    @PUT("/product/{id}")
    fun savePut(@Path("id")id: String, @Body post: ProductItem): Call<ProductItem>

    @DELETE("/product/{id}")
    fun deletePost(@Path("id")id: String): Call<Void>
}