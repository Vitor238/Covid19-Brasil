package com.vitor238.covid19brasil.data.network

import com.google.gson.annotations.SerializedName
import com.vitor238.covid19brasil.data.database.DatabaseBrazil
import com.vitor238.covid19brasil.data.database.DatabaseBrazilianState

data class NetworkBrazilianStatesList(val data: List<NetworkBrazilianState>)

data class NetworkBrazilianState(
    val uid: Int,
    val uf: String,
    val state: String,
    val cases: Int,
    val deaths: Int,
    val suspects: Int,
    val refuses: Int,
    val datetime: String
)

fun NetworkBrazilianStatesList.toDatabaseModel(): Array<DatabaseBrazilianState> {
    val list = data.map {
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
    }
    return list.toTypedArray()
}


data class NetworkCountry (
    val data: NetworkBrazil
)


data class NetworkBrazil(
    val country: String,
    val cases: Int,
    val confirmed: Int,
    val deaths: Int,
    val recovered: Int,
    @SerializedName("updated_at")
    val updatedAt: String
)

fun NetworkBrazil.toDatabaseModel() : DatabaseBrazil {
    return DatabaseBrazil(
        country = this.country,
        cases = this.cases,
        confirmed = this.confirmed,
        deaths = this.deaths,
        recovered = this.recovered,
        updatedAt = this.updatedAt
    )
}