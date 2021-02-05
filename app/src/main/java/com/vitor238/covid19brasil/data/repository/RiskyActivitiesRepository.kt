package com.vitor238.covid19brasil.data.repository

import com.vitor238.covid19brasil.R
import com.vitor238.covid19brasil.data.domain.RiskyActivity

class RiskyActivitiesRepository {
    fun getRiskyActivities() = listOf(
        RiskyActivity(
            R.string.risk_1,
            R.color.risk_1,
            R.string.risky_activity_list_1
        ),
        RiskyActivity(
            R.string.risk_2,
            R.color.risk_2,
            R.string.risky_activity_list_2
        ),
        RiskyActivity(
            R.string.risk_3,
            R.color.risk_3,
            R.string.risky_activity_list_3
        ),
        RiskyActivity(
            R.string.risk_4,
            R.color.risk_4,
            R.string.risky_activity_list_4
        ),
        RiskyActivity(
            R.string.risk_5,
            R.color.risk_5,
            R.string.risky_activity_list_5
        ),
        RiskyActivity(
            R.string.risk_6,
            R.color.risk_6,
            R.string.risky_activity_list_6
        ),
        RiskyActivity(
            R.string.risk_7,
            R.color.risk_7,
            R.string.risky_activity_list_7
        ),
        RiskyActivity(
            R.string.risk_8,
            R.color.risk_8,
            R.string.risky_activity_list_8
        ),
        RiskyActivity(
            R.string.risk_9,
            R.color.risk_9,
            R.string.risky_activity_list_9
        )
    )
}