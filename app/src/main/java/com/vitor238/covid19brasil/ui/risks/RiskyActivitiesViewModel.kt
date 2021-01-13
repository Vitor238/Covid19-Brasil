package com.vitor238.covid19brasil.ui.risks

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vitor238.covid19brasil.data.model.RiskyActivity
import com.vitor238.covid19brasil.data.repository.RiskyActivitiesRepository

class RiskyActivitiesViewModel : ViewModel() {
    private val riskyActivitiesRepository = RiskyActivitiesRepository()
    private var _riskyActivities = MutableLiveData<List<RiskyActivity>>()
    val riskyActivities: LiveData<List<RiskyActivity>>
        get() = _riskyActivities

    private fun getRiskyActivities() {
        _riskyActivities.value = riskyActivitiesRepository.getRiskyActivities()
    }

    init {
        getRiskyActivities()
    }
}