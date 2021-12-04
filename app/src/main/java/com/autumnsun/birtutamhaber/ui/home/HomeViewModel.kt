package com.autumnsun.birtutamhaber.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.autumnsun.birtutamhaber.data.remote.model.RemoteData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

/*
 Created by Fatih Kurcenli on 12/4/2021
*/

@HiltViewModel
class HomeViewModel @Inject constructor(val newsRepo: HomeRepository) : ViewModel() {

    private val _tabuData = MutableLiveData<List<RemoteData.Data.Haberler>>()
    val randomTabuData: LiveData<List<RemoteData.Data.Haberler>> = _tabuData

    fun getNewsData() = viewModelScope.launch(Dispatchers.IO) {
        val responseData = newsRepo.getNewsData()
        _tabuData.postValue(responseData)
    }

}