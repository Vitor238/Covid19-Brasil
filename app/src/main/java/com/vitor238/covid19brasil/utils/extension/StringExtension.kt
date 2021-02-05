package com.vitor238.covid19brasil.utils.extension

import android.text.Html
import android.text.Spanned
import androidx.core.text.HtmlCompat
import java.text.SimpleDateFormat
import java.util.*


fun String.formatDate(): String {
    var simpleDateFormat = SimpleDateFormat(
        "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",
        Locale.getDefault()
    )
    simpleDateFormat.timeZone = TimeZone.getTimeZone("UTC")

    return try {
        val data = simpleDateFormat.parse(this)
        simpleDateFormat = SimpleDateFormat("dd MMM yyyy - HH:mm", Locale.getDefault())
        simpleDateFormat.timeZone = TimeZone.getDefault()
        simpleDateFormat.format(data)
    } catch (e: Exception) {
        e.printStackTrace()
        ""
    }
}

fun String.fromHTML():Spanned{
    return HtmlCompat.fromHtml(
        this,
        HtmlCompat.FROM_HTML_MODE_LEGACY
    )
}