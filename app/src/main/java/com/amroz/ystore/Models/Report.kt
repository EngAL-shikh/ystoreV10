package com.amroz.ystore.Models

import org.w3c.dom.Text

data class Report(
    var report_id:Int,
    var report_reason: String,
    var author:String
) {
}