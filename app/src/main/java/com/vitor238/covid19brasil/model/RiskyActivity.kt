package com.vitor238.covid19brasil.model

import android.os.Parcelable
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import kotlinx.android.parcel.Parcelize

data class RiskyActivity(
    val title: String,
    @ColorInt
    val backgroundColor: Int,
    val riskyActivities: String,
    @ColorInt
    val textColor: Int
)