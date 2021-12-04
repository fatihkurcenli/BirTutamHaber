package com.autumnsun.birtutamhaber.ui.home

import android.util.Log
import com.autumnsun.birtutamhaber.data.remote.NewsApi
import com.autumnsun.birtutamhaber.data.remote.model.RemoteData
import javax.inject.Inject

/*
 Created by Fatih Kurcenli on 12/4/2021
*/

class HomeRepository @Inject constructor(val newsApi: NewsApi) : NewsRepo {

    override suspend fun getNewsData(): List<RemoteData.Data.Haberler> {
        val response = newsApi.getNews()
        if (response.isSuccessful) {
            Log.d("repo", response.body.toString())
        }
        return response.body.data.haberler
    }
}