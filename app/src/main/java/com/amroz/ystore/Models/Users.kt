package com.amroz.ystore.Models

import com.google.gson.annotations.SerializedName

data class Users (
    @SerializedName("user_id")
    var user_id: Int =0,
    @SerializedName("name")
    var name:String="",
    @SerializedName("email")
    var email:String="",
    @SerializedName("phone")
    var phone:String="",
    @SerializedName("address")
    var address:String="" ,
    @SerializedName("user_report")
    var user_report:Int=0
//
){
}