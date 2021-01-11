package com.amroz.ystore


import com.amroz.ystore.Models.Cart
import com.amroz.ystore.Models.Products
import com.amroz.ystore.Models.Report
import com.amroz.ystore.Models.Users
import com.google.gson.annotations.SerializedName


data class Response (
    @SerializedName("getAllReports")
    var reports:List<Report>,
    @SerializedName("getAllCat")
    var cats: List<Category>,
    @SerializedName("getAllCart")
    var carts: List<Cart>,
    @SerializedName("getAllProducts")
    var products: List<Products>,
    @SerializedName("getAllUsers")
    var users: List<Users>
){


}
