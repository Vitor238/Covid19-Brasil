package com.vitor238.covid19brasil.data.model

data class BrazilianState(
	val uid: Int,
	val uf: String,
	val state: String,
	val cases: String,
	val deaths: String,
	val suspects: String,
	val refuses: String,
	val datetime: String
)