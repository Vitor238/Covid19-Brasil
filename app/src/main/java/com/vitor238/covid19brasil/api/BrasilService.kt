package com.vitor238.covid19brasil.api

import com.vitor238.covid19brasil.model.Pais
import retrofit2.http.GET

interface BrasilService {
    @GET("api/report/v1/brazil")
    fun casosBrasil(): retrofit2.Call<Pais>
}