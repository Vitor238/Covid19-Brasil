package com.vitor238.covid19brasil.data.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class UsefulLink(
    @StringRes
    val title: Int,
    @StringRes
    val author: Int?,
    @DrawableRes
    val thumbnail: Int,
    val link: String
)