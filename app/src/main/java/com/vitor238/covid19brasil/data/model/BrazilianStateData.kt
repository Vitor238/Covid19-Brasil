package com.vitor238.covid19brasil.data.model


import com.google.gson.annotations.SerializedName

data class BrazilianStateData(
    @SerializedName("cases")
    val cases: Int,
    @SerializedName("datetime")
    val datetime: String,
    @SerializedName("deaths")
    val deaths: Int,
    @SerializedName("refuses")
    val refuses: Int,
    @SerializedName("state")
    val state: String,
    @SerializedName("suspects")
    val suspects: Int,
    @SerializedName("uf")
    val uf: String,
    @SerializedName("uid")
    val uid: Int
)