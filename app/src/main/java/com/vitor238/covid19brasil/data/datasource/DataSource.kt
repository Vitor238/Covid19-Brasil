package com.vitor238.covid19brasil.data.datasource

import com.vitor238.covid19brasil.data.database.CasesByStateDao
import com.vitor238.covid19brasil.data.database.TotalCasesInBrazilDao
import com.vitor238.covid19brasil.data.model.BrazilianStateData
import com.vitor238.covid19brasil.data.model.CountryData

interface DataSource {
    interface Local {
        fun getTotalCasesInBrazil(): TotalCasesInBrazilDao
        fun getCasesByState(): CasesByStateDao
    }

    interface Remote {
        suspend fun getBrazilianStates(): List<BrazilianStateData>
        suspend fun getCasesInBrazil(): CountryData
    }
}