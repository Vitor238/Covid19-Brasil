package com.vitor238.covid19brasil.api

import com.vitor238.covid19brasil.model.Country
import retrofit2.http.GET

interface BrazilService {
    @GET("api/report/v1/brazil")
    fun casesBrazil(): retrofit2.Call<Country>
}