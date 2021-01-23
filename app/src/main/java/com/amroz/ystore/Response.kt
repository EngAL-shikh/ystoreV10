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
    @SerializedName("getProductsInCart")
    var carts: List<Products>,
    @SerializedName("getAllProducts")
    var products: List<Products>,
    @SerializedName("getAllUsers")
    var users: List<Users>,
    @SerializedName("getSingleProductByCat")
    var productByCat: List<Products>,
    @SerializedName("getSingleUser")
    var singleUsers: List<Users>,
    @SerializedName("code")
    var code:String,
    @SerializedName("messege")
    var result:String


){


}


