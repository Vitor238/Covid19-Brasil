package com.vitor238.covid19brasil.data.api

import com.vitor238.covid19brasil.data.model.BrazilianStatesListResponse
import retrofit2.http.GET

interface BrazilianStatesService {
    @GET("/api/report/v1")
    suspend fun casesByState(): BrazilianStatesListResponse
}