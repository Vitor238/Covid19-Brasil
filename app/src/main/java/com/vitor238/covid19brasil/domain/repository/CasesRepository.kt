package com.vitor238.covid19brasil.domain.repository

import com.vitor238.covid19brasil.data.database.DatabaseBrazil
import com.vitor238.covid19brasil.data.database.DatabaseBrazilianState

interface CasesRepository {
    suspend fun getCasesInBrazil(): DatabaseBrazil
    suspend fun getCasesByState(): List<DatabaseBrazilianState>
    suspend fun refreshCasesByState()
    suspend fun refreshCasesInBrazil()
}