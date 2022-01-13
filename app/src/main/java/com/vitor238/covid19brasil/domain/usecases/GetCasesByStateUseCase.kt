package com.vitor238.covid19brasil.domain.usecases

import android.util.Log
import com.vitor238.covid19brasil.data.database.toDomainModel
import com.vitor238.covid19brasil.domain.model.BrazilianState
import com.vitor238.covid19brasil.domain.repository.CasesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow


class GetCasesByStateUseCase(private val casesRepository: CasesRepository) {
    suspend operator fun invoke(): Flow<List<BrazilianState>> = flow {
        casesRepository.refreshCasesByState()
        casesRepository.getCasesByState().catch { e ->
            Log.i(GetCasesByStateUseCase::class.simpleName, "invoke: ${e.message} ")
        }.collect { list ->
            emit(list.sortedByDescending { it.cases }.toDomainModel())
        }
    }
}