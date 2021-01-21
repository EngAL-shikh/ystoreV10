package com.amroz.ystore.Models

import java.io.Serializable

class UserChat (
    val uid: String = "",
    val userName: String = "",
    var rooms: MutableMap<String, Any>? = null
) : Serializable