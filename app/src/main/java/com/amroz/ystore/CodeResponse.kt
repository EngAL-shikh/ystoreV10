package com.amroz.ystore

import com.google.gson.annotations.SerializedName

data class CodeResponse(
    @SerializedName("code") var code:String,
    @SerializedName("messege") var result:String

//

)