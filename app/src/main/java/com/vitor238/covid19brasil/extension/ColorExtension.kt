package com.vitor238.covid19brasil.extension

import android.content.Context
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

/**
 * Extension method to provide simpler access to {@link ContextCompat#getColor(int)}.
 */
fun Context.getColorCompat(color: Int) = ContextCompat.getColor(this, color)

/**
 *  Extension method to provide simpler access to {@link ContextCompat#getColor(int)}
 *  from a [Fragment].
 */
fun Fragment.getColorCompat(color: Int) = requireContext().getColorCompat(color)