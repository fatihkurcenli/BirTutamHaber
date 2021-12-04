package com.autumnsun.birtutamhaber.ui.detail

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
class DetailFragmentViewModel @Inject constructor() : ViewModel() {
    private val _detailNews = MutableLiveData<RemoteData.Data.Haberler>()
    val detailNews: LiveData<RemoteData.Data.Haberler> = _detailNews


    fun setDetailNewsWithArgs(newsDetail: RemoteData.Data.Haberler) =
        viewModelScope.launch(Dispatchers.IO) {
            _detailNews.postValue(newsDetail)
        }

}