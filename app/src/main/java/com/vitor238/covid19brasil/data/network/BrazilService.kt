package com.vitor238.covid19brasil.data.network

import retrofit2.http.GET

interface BrazilService {
    @GET("api/report/v1/brazil")
    suspend fun totalCasesBrazil(): NetworkCountry
}