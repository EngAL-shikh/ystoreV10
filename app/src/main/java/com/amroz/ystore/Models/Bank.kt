package com.amroz.ystore.Models

import com.google.gson.annotations.SerializedName

data class Bank (

    @SerializedName("Payment_id")
    var Payment_id: Int =0,
    @SerializedName("number")
    var number: Int =0,
    @SerializedName("name")
    var name: String ="",
    @SerializedName("cvv")
    var cvv: Int =0,
    @SerializedName("Expiry_date")
    var Expiry_date: String ="",
    @SerializedName("amount")
    var amount: Float =3F

){
}