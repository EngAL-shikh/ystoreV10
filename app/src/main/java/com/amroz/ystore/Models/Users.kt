package com.amroz.ystore.Models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Users (
    @SerializedName("user_id")
    var user_id: Int =0,
    @SerializedName("name")
    var name:String="",
    @SerializedName("email")
    var email:String="",
    @SerializedName("password")
    var password:String="",
    @SerializedName("chat_id")
    var chat_id:String="",
    @SerializedName("rule")
    var rule:String="",
    @SerializedName("phone")
    var phone:String="",
    @SerializedName("address")
    var address:String="" ,
    @SerializedName("user_report")
    var user_report:Int=0,
    @SerializedName("user_image")
    var user_image:String="",
    @SerializedName("user_raiting")
    var user_raiting:Int=0,
    @SerializedName("password")
var password:String=""
//
): Serializable {
}