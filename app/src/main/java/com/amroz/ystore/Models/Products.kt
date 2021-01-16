package com.amroz.ystore.Models

import com.google.gson.annotations.SerializedName
import java.util.*

data class Products(
    @SerializedName("product_id")
    var product_id:Int=0,
    @SerializedName("title")
    var title:String="",
    @SerializedName("details")
    var details: String="",
    @SerializedName("images")
    var images: String="",
    @SerializedName("color")
    var color:String="",
    @SerializedName("product_features")
    var product_features:String="" ,
    @SerializedName("rating")
    var rating:Int=0,
    @SerializedName("price_y")
    var price_y:Int=0,
    @SerializedName("price_d")
    var price_d:Int=0,
    @SerializedName("user_id")
    var user_id:Int=0,
    @SerializedName("cat_id")
    var cat_id:Int=0 ,
    @SerializedName("reports_id")
    var reports_id:Int=0
) {
}