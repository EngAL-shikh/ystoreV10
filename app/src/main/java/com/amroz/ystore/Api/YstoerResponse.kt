package com.amroz.ystore.Api

import com.amroz.ystore.Models.Report
import com.google.gson.annotations.SerializedName


data class YstoerResponse (
    @SerializedName("getAllRows")
    var reports:List<Report>
){


}