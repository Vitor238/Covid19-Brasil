package com.vitor238.covid19brasil.utils.extension

import java.text.NumberFormat

fun Int.formatNumber():String{
    return NumberFormat.getNumberInstance().format(this)
}

