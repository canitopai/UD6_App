package com.canitopai.ud6_recu_app.data.model


import com.google.gson.annotations.SerializedName

data class ProductItem(
    @SerializedName("available")
    val available: Boolean,
    @SerializedName("description")
    val description: String,
    @SerializedName("discountPrice")
    val discountPrice: Double,
    @SerializedName("id")
    val id: String,
    @SerializedName("imageUrl")
    val imageUrl: String,
    @SerializedName("name")
    var name: String,
    @SerializedName("regularPrice")
    val regularPrice: Double,
    @SerializedName("stock")
    val stock: Int
)