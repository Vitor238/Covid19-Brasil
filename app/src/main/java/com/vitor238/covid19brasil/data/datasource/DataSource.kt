package com.vitor238.covid19brasil.data.datasource

import com.vitor238.covid19brasil.data.database.CasesByStateDao
import com.vitor238.covid19brasil.data.database.TotalCasesInBrazilDao
import com.vitor238.covid19brasil.data.model.BrazilianStateData
import com.vitor238.covid19brasil.data.model.CountryData
import kotlinx.coroutines.flow.Flow

interface DataSource {
    interface Local {
        fun getCasesInBrazilDao(): TotalCasesInBrazilDao
        fun getCasesByStateDao(): CasesByStateDao
    }

    interface Remote {
        suspend fun getBrazilianStates(): Flow<List<BrazilianStateData>>
        suspend fun getCasesInBrazil(): Flow<CountryData>
    }
}