package com.vitor238.covid19brasil.data.repository

import com.vitor238.covid19brasil.data.api.BrazilService
import com.vitor238.covid19brasil.data.api.BrazilianStatesService
import com.vitor238.covid19brasil.data.model.BrazilResponse
import com.vitor238.covid19brasil.data.model.BrazilianStatesListResponse
import com.vitor238.covid19brasil.utils.RetrofitInitializer

class StatisticsRepository {

    private val retrofit = RetrofitInitializer().getRetrofit()


    suspend fun loadCasesBrazil(): BrazilResponse {
        return retrofit.create(BrazilService::class.java).casesBrazil().data
    }

    suspend fun loadCasesByState(): BrazilianStatesListResponse {
        return retrofit.create(BrazilianStatesService::class.java).casesByState()
    }
}