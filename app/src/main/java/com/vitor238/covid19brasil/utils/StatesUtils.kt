package com.vitor238.covid19brasil.utils

import androidx.annotation.DrawableRes
import com.vitor238.covid19brasil.R

object StatesUtils {
    @DrawableRes
    fun getFlag(uf: String): Int? {
        return when (uf) {
            "AC" -> R.drawable.flag_ac
            "AL" -> R.drawable.flag_al
            "AP" -> R.drawable.flag_ap
            "AM" -> R.drawable.flag_am
            "BA" -> R.drawable.flag_ba
            "CE" -> R.drawable.flag_ce
            "DF" -> R.drawable.flag_df
            "ES" -> R.drawable.flag_es
            "GO" -> R.drawable.flag_go
            "MA" -> R.drawable.flag_ma
            "MG" -> R.drawable.flag_mg
            "MT" -> R.drawable.flag_mt
            "MS" -> R.drawable.flag_ms
            "PA" -> R.drawable.flag_pa
            "PB" -> R.drawable.flag_pb
            "PR" -> R.drawable.flag_pr
            "PE" -> R.drawable.flag_pe
            "PI" -> R.drawable.flag_pi
            "RJ" -> R.drawable.flag_rj
            "RN" -> R.drawable.flag_rn
            "RS" -> R.drawable.flag_rs
            "RO" -> R.drawable.flag_ro
            "RR" -> R.drawable.flag_rr
            "SC" -> R.drawable.flag_sc
            "SP" -> R.drawable.flag_sp
            "SE" -> R.drawable.flag_se
            "TO" -> R.drawable.flag_to
            else -> null
        }
    }
}