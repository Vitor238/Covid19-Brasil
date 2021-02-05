package com.vitor238.covid19brasil.data.domain

import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.StringRes

data class RiskyActivity(
    @StringRes
    val title: Int,
    @ColorRes
    val backgroundColor: Int,
    @StringRes
    val riskyActivities: Int
)