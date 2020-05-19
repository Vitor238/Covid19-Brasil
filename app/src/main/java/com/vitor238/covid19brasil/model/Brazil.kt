package com.vitor238.covid19brasil.model

data class Brazil(
    val country: String = "",
    val cases: Int = 0,
    val confirmed: Int = 0,
    val deaths: Int = 0,
    val recovered: Int = 0,
    val updated_at: String = ""
)