package com.vitor238.covid19brasil.data.model

data class BrazilianStateResponse(
	val uid: Int,
	val uf: String,
	val state: String,
	val cases: Int,
	val deaths: Int,
	val suspects: Int,
	val refuses: Int,
	val datetime: String
)