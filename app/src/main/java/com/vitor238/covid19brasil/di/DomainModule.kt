package com.vitor238.covid19brasil.di

import com.vitor238.covid19brasil.domain.repository.CasesRepository
import com.vitor238.covid19brasil.domain.usecases.GetCasesByStateUseCase
import com.vitor238.covid19brasil.domain.usecases.GetCasesInBrazilUseCase
import org.koin.dsl.module

val domainModule = module {
    single { provideGetCasesByStateUseCase(get()) }
    single { provideGetCasesInBrazilUseCase(get()) }
}

fun provideGetCasesByStateUseCase(repository: CasesRepository): GetCasesByStateUseCase {
    return GetCasesByStateUseCase(repository)
}

fun provideGetCasesInBrazilUseCase(repository: CasesRepository): GetCasesInBrazilUseCase {
    return GetCasesInBrazilUseCase(repository)
}