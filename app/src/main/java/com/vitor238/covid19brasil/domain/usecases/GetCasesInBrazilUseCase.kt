package com.vitor238.covid19brasil.domain.usecases

import android.util.Log
import com.vitor238.covid19brasil.data.database.toDomainModel
import com.vitor238.covid19brasil.domain.model.Brazil
import com.vitor238.covid19brasil.domain.repository.CasesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

class GetCasesInBrazilUseCase(private val casesRepository: CasesRepository) {
    suspend operator fun invoke(): Flow<Brazil> = flow {
        casesRepository.refreshCasesInBrazil()
        casesRepository.getCasesInBrazil()
            .catch { e ->
                Log.i("UC", "invoke: ${e.message} ")
            }.collect { cases ->
                emit(cases.toDomainModel())
            }
    }
}