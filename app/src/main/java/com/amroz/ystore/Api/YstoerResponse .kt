package com.amroz.ystore.Api


import com.amroz.ystore.Category
import com.amroz.ystore.Models.Cart
import com.google.gson.annotations.SerializedName


data class CategoryResponse (
    @SerializedName("getAllCat")
    var Cat: List<Category>

)

data class CartResponse (
    @SerializedName("getAllCart")
    var Cart: List<Cart>
)
