package com.vitor238.covid19brasil.api

import com.vitor238.covid19brasil.model.ListBrazilianStates
import retrofit2.Call
import retrofit2.http.GET

interface BrazilianStatesService {
    @GET("/api/report/v1")
    fun casesByState(): Call<ListBrazilianStates>
}