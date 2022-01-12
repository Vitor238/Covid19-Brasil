package com.vitor238.covid19brasil.domain.repository

import com.vitor238.covid19brasil.data.database.DatabaseBrazil
import com.vitor238.covid19brasil.data.database.DatabaseBrazilianState
import kotlinx.coroutines.flow.Flow

interface CasesRepository {
    suspend fun getCasesInBrazil(): Flow<DatabaseBrazil>
    suspend fun getCasesByState(): Flow<List<DatabaseBrazilianState>>
    suspend fun refreshCasesByState()
    suspend fun refreshCasesInBrazil()
}