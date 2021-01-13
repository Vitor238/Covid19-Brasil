package com.vitor238.covid19brasil.ui.cases

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vitor238.covid19brasil.data.model.Brazil
import com.vitor238.covid19brasil.data.model.BrazilianState
import com.vitor238.covid19brasil.data.repository.StatisticsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CasesViewModel : ViewModel() {

    private val statisticsRepository = StatisticsRepository()
    private var _casesBrazil = MutableLiveData<Brazil>()
    val casesBrazil: LiveData<Brazil>
        get() = _casesBrazil
    private var _casesByState = MutableLiveData<List<BrazilianState>>()
    val casesByState: LiveData<List<BrazilianState>>
        get() = _casesByState

    private fun getCasesBrazil() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = kotlin.runCatching { statisticsRepository.loadCasesBrazil() }
            result.onSuccess {
                withContext(Dispatchers.Main) {
                    _casesBrazil.value = it.toBrazil()
                }
            }.onFailure {
                it.printStackTrace()
            }
        }

    }

    private fun getCasesByState() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = kotlin.runCatching { statisticsRepository.loadCasesByState() }
            result.onSuccess {
                withContext(Dispatchers.Main) {
                    _casesByState.value = it.toBrazilianStatesList()
                }
            }.onFailure {
                it.printStackTrace()
            }
        }
    }

    init {
        getCasesBrazil()
        getCasesByState()
    }
}