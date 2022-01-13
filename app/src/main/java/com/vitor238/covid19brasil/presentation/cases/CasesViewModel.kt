package com.vitor238.covid19brasil.presentation.cases

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vitor238.covid19brasil.domain.model.Brazil
import com.vitor238.covid19brasil.domain.model.BrazilianState
import com.vitor238.covid19brasil.domain.usecases.GetCasesByStateUseCase
import com.vitor238.covid19brasil.domain.usecases.GetCasesInBrazilUseCase
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class CasesViewModel(
    private val getCasesInBrazilUseCase: GetCasesInBrazilUseCase,
    private val getCasesByStateUseCase: GetCasesByStateUseCase
) : ViewModel() {

    private var _casesByState = MutableLiveData<List<BrazilianState>>()
    val casesByState: LiveData<List<BrazilianState>>
        get() = _casesByState

    private var _casesInBrazil = MutableLiveData<Brazil>()
    val casesInBrazil: LiveData<Brazil>
        get() = _casesInBrazil

    private var _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String>
        get() = _errorMessage


    fun getCasesByState() {
        viewModelScope.launch {
            getCasesByStateUseCase()
                .catch { e ->
                    _errorMessage.value = e.localizedMessage ?: e.message
                }
                .collect {
                    _casesByState.value = it
                }
        }
    }

    fun getCasesInBrazil() {
        viewModelScope.launch {
            getCasesInBrazilUseCase()
                .catch { e ->
                    _errorMessage.value = e.localizedMessage ?: e.message
                }.collect {
                    _casesInBrazil.value = it
                }
        }
    }
}