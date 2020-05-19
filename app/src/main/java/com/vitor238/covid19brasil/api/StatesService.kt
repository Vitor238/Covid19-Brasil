package com.vitor238.covid19brasil.api

import com.vitor238.covid19brasil.model.ListStates
import retrofit2.Call
import retrofit2.http.GET

interface StatesService {
    @GET("/api/report/v1")
    fun casesByState(): Call<ListStates>
}