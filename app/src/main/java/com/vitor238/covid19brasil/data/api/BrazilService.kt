package com.vitor238.covid19brasil.data.api

import com.vitor238.covid19brasil.data.model.Country
import retrofit2.http.GET

interface BrazilService {
    @GET("api/report/v1/brazil")
    suspend fun casesBrazil(): Country
}