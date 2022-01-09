package com.vitor238.covid19brasil.data.model


import com.google.gson.annotations.SerializedName

data class BrazilianState(
    @SerializedName("data")
    val `data`: List<BrazilianStateData>
)