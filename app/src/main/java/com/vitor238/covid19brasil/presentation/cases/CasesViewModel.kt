package com.vitor238.covid19brasil.presentation.cases

import android.app.Application
import androidx.lifecycle.AndroidViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.vitor238.covid19brasil.data.database.getDatabase
import com.vitor238.covid19brasil.data.repository.CasesRepository
import kotlinx.coroutines.launch

class CasesViewModel(application: Application) : AndroidViewModel(application) {

    private val database = getDatabase(application)
    private val casesRepository = CasesRepository(database)

    init {
        viewModelScope.launch {
            casesRepository.refreshCasesByState()
            casesRepository.refreshCasesInBrazil()
        }
    }

    val casesByState = casesRepository.casesByState
    val casesInBrazil = casesRepository.casesInBrazil

    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(CasesViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return CasesViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}