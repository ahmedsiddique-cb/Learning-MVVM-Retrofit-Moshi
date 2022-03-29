package com.example.hiltmvvmflowmoshi.data

import com.squareup.moshi.Json

data class Dogs(
    @Json(name ="url")
    var url:String = ""
)
