package com.vitor238.covid19brasil.api

import com.vitor238.covid19brasil.model.ListaEstados
import retrofit2.Call
import retrofit2.http.GET

interface EstadosService {
    @GET("/api/report/v1")
    fun casosPorEstado(): Call<ListaEstados>
}