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
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
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


    fun getCasesByState() {
        viewModelScope.launch {
            getCasesByStateUseCase()
                .collect {
                    _casesByState.value = it
                }
        }
    }

    fun getCasesInBrazil() {
        viewModelScope.launch {
            getCasesInBrazilUseCase()
                .onEach {
                    _casesInBrazil.value = it
                }
                .catch {

                }
                .launchIn(viewModelScope)
        }
    }
}