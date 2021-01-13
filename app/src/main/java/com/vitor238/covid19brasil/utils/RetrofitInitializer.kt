package com.vitor238.covid19brasil.utils

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInitializer {
    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://covid19-brazil-api.now.sh/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}