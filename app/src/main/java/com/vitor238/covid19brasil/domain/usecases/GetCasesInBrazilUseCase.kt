package com.vitor238.covid19brasil.domain.usecases

import com.vitor238.covid19brasil.data.database.toDomainModel
import com.vitor238.covid19brasil.domain.model.Brazil
import com.vitor238.covid19brasil.domain.repository.CasesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetCasesInBrazilUseCase(private val casesRepository: CasesRepository) {
    suspend operator fun invoke(): Flow<Brazil> = flow {
        casesRepository.getCasesInBrazil()
        try {
            casesRepository.refreshCasesInBrazil()
            val cases = casesRepository.getCasesInBrazil().toDomainModel()
            emit(cases)
        } catch (e: Exception) {

        }
    }
}