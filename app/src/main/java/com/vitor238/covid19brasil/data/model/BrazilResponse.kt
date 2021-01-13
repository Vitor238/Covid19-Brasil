package com.vitor238.covid19brasil.data.model

import com.google.gson.annotations.SerializedName
import com.vitor238.covid19brasil.utils.extension.formatDate
import com.vitor238.covid19brasil.utils.extension.formatNumber

data class BrazilResponse(
    val country: String,
    val cases: Int,
    val confirmed: Int,
    val deaths: Int,
    val recovered: Int,
    @SerializedName("updated_at")
    val updatedAt: String
) {

    fun toBrazil(): Brazil {
        return Brazil(
            cases = this.cases.formatNumber(),
            confirmed = this.confirmed.formatNumber(),
            deaths = this.deaths.formatNumber(),
            recovered = this.recovered.formatNumber(),
            updatedAt = updatedAt.formatDate()
        )
    }
}