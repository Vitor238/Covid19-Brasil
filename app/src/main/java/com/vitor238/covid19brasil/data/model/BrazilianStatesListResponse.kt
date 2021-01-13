package com.vitor238.covid19brasil.data.model

import com.vitor238.covid19brasil.utils.extension.formatDate
import com.vitor238.covid19brasil.utils.extension.formatNumber

data class BrazilianStatesListResponse(
    val data: List<BrazilianStateResponse>) {
    fun toBrazilianStatesList(): List<BrazilianState> {
        val brazilianStates = mutableListOf<BrazilianState>()
        this.data.forEach {
            val brazilianState = BrazilianState(
                uid = it.uid,
                uf = it.uf,
                state = it.state,
                cases = it.cases.formatNumber(),
                deaths = it.cases.formatNumber(),
                suspects = it.cases.formatNumber(),
                refuses = it.cases.formatNumber(),
                datetime = it.datetime.formatDate()
            )
            brazilianStates.add(brazilianState)
        }
        return brazilianStates
    }
}