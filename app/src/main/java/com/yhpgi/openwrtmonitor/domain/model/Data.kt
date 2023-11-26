package com.yhpgi.openwrtmonitor.domain.model


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("board_name")
    val boardName: String, // amlogic,p212
    @SerializedName("hostname")
    val hostname: String, // terongWrt
    @SerializedName("kernel")
    val kernel: String, // 5.4
    @SerializedName("model")
    val model: String, // Amlogic Meson GXL (S905X) P212 Development Board
    @SerializedName("release")
    val release: Release,
    @SerializedName("system")
    val system: String // ARMv8 Processor rev 4
)