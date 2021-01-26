package com.amroz.ystore.Models

import com.google.gson.annotations.SerializedName

data class RatingUs (
    @SerializedName("rating_id")
    val rating_id:Int? =null,
    @SerializedName("ratingNum")
    val ratingNum:Float=0.0f,
    @SerializedName("product_id")
    val product_id:Int=0,
    @SerializedName("user_id")
    val user_id:Int=0
   )
