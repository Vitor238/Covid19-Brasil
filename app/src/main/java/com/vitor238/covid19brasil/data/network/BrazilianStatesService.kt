package com.vitor238.covid19brasil.data.network

import retrofit2.http.GET

interface BrazilianStatesService {
    @GET("/api/report/v1")
    suspend fun casesByState(): NetworkBrazilianStatesList
}