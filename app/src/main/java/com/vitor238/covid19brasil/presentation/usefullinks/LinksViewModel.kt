package com.vitor238.covid19brasil.presentation.usefullinks

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vitor238.covid19brasil.data.repository.UsefulLinksRepository
import com.vitor238.covid19brasil.domain.model.UsefulLink

class LinksViewModel : ViewModel() {
    private val usefulLinksRepository = UsefulLinksRepository()
    private var _usefulLinks = MutableLiveData<List<UsefulLink>>()
    val usefulLinks: LiveData<List<UsefulLink>>
        get() = _usefulLinks

    private fun getLinks(){
        _usefulLinks.value = usefulLinksRepository.getLinks()
    }

    init {
        getLinks()
    }
}