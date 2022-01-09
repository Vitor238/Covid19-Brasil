package com.vitor238.covid19brasil.data.model


import com.google.gson.annotations.SerializedName

data class CountryData(
    @SerializedName("cases")
    val cases: Int?,
    @SerializedName("confirmed")
    val confirmed: Int?,
    @SerializedName("country")
    val country: String,
    @SerializedName("deaths")
    val deaths: Int?,
    @SerializedName("recovered")
    val recovered: Int?,
    @SerializedName("updated_at")
    val updatedAt: String
)