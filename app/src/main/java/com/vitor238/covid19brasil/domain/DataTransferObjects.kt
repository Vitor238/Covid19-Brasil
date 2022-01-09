package com.vitor238.covid19brasil.domain

import com.vitor238.covid19brasil.data.database.DatabaseBrazil
import com.vitor238.covid19brasil.data.database.DatabaseBrazilianState
import com.vitor238.covid19brasil.data.model.BrazilianStateData
import com.vitor238.covid19brasil.data.model.CountryData

fun List<BrazilianStateData>.toDatabaseModel(): Array<DatabaseBrazilianState> {
    return this.map {
        DatabaseBrazilianState(
            uid = it.uid,
            uf = it.uf,
            state = it.state,
            cases = it.cases,
            deaths = it.deaths,
            suspects = it.suspects,
            refuses = it.refuses,
            datetime = it.datetime
        )
    }.toTypedArray()
}

fun CountryData.toDatabaseModel(): DatabaseBrazil {
    return DatabaseBrazil(
        country = country,
        cases = cases ?: 0,
        confirmed = confirmed ?: 0,
        deaths = deaths ?: 0,
        recovered = recovered ?: 0,
        updatedAt = updatedAt
    )
}