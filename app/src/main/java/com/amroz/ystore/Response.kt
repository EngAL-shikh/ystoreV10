package com.amroz.ystore


import android.media.Rating
import com.amroz.ystore.Models.*
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
    @SerializedName("getSingleProductByUser")
    var productByUser: List<Products>,
    @SerializedName("getSingleUser")
    var singleUsers: List<Users>,
    @SerializedName("code")
    var code:String,
    @SerializedName("messege")
    var result:String,
    @SerializedName("getAllRating")
    var rating: List<RatingUs>,
    @SerializedName("getSingleuUserid")
    var getSingleuUserid: List<Users>,

    @SerializedName("getUserIDByEmail")
    var getUserIDByEmail: List<Users>

){


}


