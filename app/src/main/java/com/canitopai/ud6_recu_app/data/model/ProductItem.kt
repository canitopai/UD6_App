package com.canitopai.ud6_recu_app.data.model


import com.google.gson.annotations.SerializedName

data class ProductItem(
    @SerializedName("available")
    val available: Boolean = true,
    @SerializedName("description")
    val description: String = "null",
    @SerializedName("discountPrice")
    val discountPrice: Double = 999.99,
    @SerializedName("id")
    val id: String,
    @SerializedName("imageUrl")
    val imageUrl: String,
    @SerializedName("name")
    var name: String = "null",
    @SerializedName("regularPrice")
    val regularPrice: Double = 0.00,
    @SerializedName("stock")
    val stock: Int = 0
)