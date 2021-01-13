package com.vitor238.covid19brasil.ui.usefullinks

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vitor238.covid19brasil.data.model.RiskyActivity
import com.vitor238.covid19brasil.data.model.UsefulLink
import com.vitor238.covid19brasil.data.repository.UsefulLinksRepository

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