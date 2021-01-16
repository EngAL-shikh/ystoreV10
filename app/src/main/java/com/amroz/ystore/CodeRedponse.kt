package com.amroz.ystore

import com.google.gson.annotations.SerializedName

data class CodeRedponse(
    @SerializedName("code") var code:String,
    @SerializedName("message") var result:String
) {
}