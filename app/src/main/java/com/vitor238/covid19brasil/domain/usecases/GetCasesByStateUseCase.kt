package com.vitor238.covid19brasil.domain.usecases

import android.util.Log
import com.vitor238.covid19brasil.data.database.toDomainModel
import com.vitor238.covid19brasil.domain.model.BrazilianState
import com.vitor238.covid19brasil.domain.repository.CasesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class GetCasesByStateUseCase(private val casesRepository: CasesRepository) {
    suspend operator fun invoke(): Flow<List<BrazilianState>> = flow {
        try {
            casesRepository.refreshCasesByState()
            val list = casesRepository.getCasesByState().sortedByDescending { it.cases }
            emit(list.toDomainModel())
        } catch (e: Exception) {
            Log.i("UC", "invoke: ${e.message} ")
        }
    }
}