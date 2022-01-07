package com.vitor238.covid19brasil.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.vitor238.covid19brasil.common.extension.formatDate
import com.vitor238.covid19brasil.common.extension.formatNumber
import com.vitor238.covid19brasil.data.domain.Brazil
import com.vitor238.covid19brasil.data.domain.BrazilianState

@Entity(tableName =  "total_cases_brazil_table")
data class DatabaseBrazil(
    @PrimaryKey
    val country:String,
    val cases: Int,
    val confirmed: Int,
    val deaths: Int,
    val recovered: Int,
    val updatedAt: String
)

fun DatabaseBrazil.toDomainModel():Brazil{
    return Brazil(
        country = this.country,
        cases = this.cases.formatNumber(),
        confirmed = this.confirmed.formatNumber(),
        deaths = this.deaths.formatNumber(),
        recovered = this.recovered.formatNumber(),
        updatedAt = this.updatedAt.formatDate()
    )
}

@Entity(tableName = "cases_by_state_table")
data class DatabaseBrazilianState(
    @PrimaryKey
    val uid: Int,
    val uf: String,
    val state: String,
    val cases: Int,
    val deaths: Int,
    val suspects: Int,
    val refuses: Int,
    val datetime: String
)

fun List<DatabaseBrazilianState>.toDomainModel(): List<BrazilianState> {
    return map {
        BrazilianState(
            uid = it.uid,
            uf = it.uf,
            state = it.state,
            cases = it.cases.formatNumber(),
            deaths = it.deaths.formatNumber(),
            suspects = it.suspects.formatNumber(),
            refuses = it.refuses.formatNumber(),
            datetime = it.datetime.formatDate()
        )
    }
}