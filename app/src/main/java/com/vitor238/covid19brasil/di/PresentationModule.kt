package com.vitor238.covid19brasil.di

import com.vitor238.covid19brasil.presentation.cases.CasesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { CasesViewModel(get(), get()) }
}