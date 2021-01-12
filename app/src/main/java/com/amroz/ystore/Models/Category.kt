package com.amroz.ystore

import com.google.gson.annotations.SerializedName
import java.util.*

data class Category (
   @SerializedName("cat_id")
   val cat_id:Int,
   @SerializedName("cat_title")
   val cat_title:String,
   @SerializedName("images")
   var images:String=""
)