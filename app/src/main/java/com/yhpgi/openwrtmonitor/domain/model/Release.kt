package com.yhpgi.openwrtmonitor.domain.model


import com.google.gson.annotations.SerializedName

data class Release(
    @SerializedName("description")
    val description: String, // OpenWrt 21.02.5 r16688-fa9a932fdb
    @SerializedName("distribution")
    val distribution: String, // OpenWrt
    @SerializedName("revision")
    val revision: String, // r16688-fa9a932fdb
    @SerializedName("target")
    val target: String, // armvirt/64
    @SerializedName("version")
    val version: String // 21.02.5
)