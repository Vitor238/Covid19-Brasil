package com.vitor238.covid19brasil.model

data class BrazilianState (
	val uid : Int,
	val uf : String,
	val state : String,
	val cases : Int,
	val deaths : Int,
	val suspects : Int,
	val refuses : Int,
	val datetime : String
)