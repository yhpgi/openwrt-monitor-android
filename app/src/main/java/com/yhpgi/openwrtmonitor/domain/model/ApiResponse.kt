package com.yhpgi.openwrtmonitor.domain.model


import com.google.gson.annotations.SerializedName

data class ApiResponse(
    @SerializedName("data")
    val `data`: List<Data>,
    @SerializedName("error")
    val error: Any, // null
    @SerializedName("status")
    val status: Boolean // true
)