package com.vitor238.covid19brasil.data.model

import androidx.annotation.ColorInt
import androidx.annotation.StringRes

data class RiskyActivity(
    @StringRes
    val title: Int,
    val backgroundColor: Int,
    @StringRes
    val riskyActivities: Int,
    val textColor: Int
)