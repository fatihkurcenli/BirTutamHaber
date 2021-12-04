package com.autumnsun.birtutamhaber.ui.writers

import android.util.Log
import com.autumnsun.birtutamhaber.data.remote.NewsApi
import com.autumnsun.birtutamhaber.data.remote.model.RemoteData
import com.autumnsun.birtutamhaber.utils.safeApiCall
import javax.inject.Inject

/*
 Created by Fatih Kurcenli on 12/4/2021
*/

class WriterRepository @Inject constructor(val newsApi: NewsApi) : WriterRepo {
    override suspend fun getWritersAll(): List<RemoteData.Data.Yazarlar> {
        val response = safeApiCall { newsApi.getAllWriters() }
        if (response.isSuccessful) {
            return response.body.yazarlar
        }
        return emptyList()
    }
}