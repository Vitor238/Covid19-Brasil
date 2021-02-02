package com.vitor238.covid19brasil.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Network {
     private val retrofit =  Retrofit.Builder()
            .baseUrl("https://covid19-brazil-api.now.sh/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    val brazil: BrazilService = retrofit.create(BrazilService::class.java)
    val brazilianStates: BrazilianStatesService = retrofit.create(BrazilianStatesService::class.java)
}