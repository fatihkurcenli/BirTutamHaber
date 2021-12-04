package com.autumnsun.birtutamhaber.ui.writers

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
class WriterViewModel @Inject constructor(val writerRepository: WriterRepository) : ViewModel() {

    private val _writers = MutableLiveData<List<RemoteData.Data.Yazarlar>>()
    val writers: LiveData<List<RemoteData.Data.Yazarlar>> = _writers

    fun getWriters() = viewModelScope.launch(Dispatchers.IO) {
        val dataFromRepo = writerRepository.getWritersAll()
        if (dataFromRepo.isNotEmpty()) {
            _writers.postValue(dataFromRepo)
        }
    }
}