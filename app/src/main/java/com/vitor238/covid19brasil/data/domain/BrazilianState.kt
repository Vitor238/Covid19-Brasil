package com.vitor238.covid19brasil.data.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class BrazilianState(
	val uid: Int,
	val uf: String,
	val state: String,
	val cases: String,
	val deaths: String,
	val suspects: String,
	val refuses: String,
	val datetime: String
) : Parcelable