package com.autumnsun.birtutamhaber.ui.detail

import android.util.Log
import com.autumnsun.birtutamhaber.data.remote.NewsApi
import com.autumnsun.birtutamhaber.data.remote.model.RemoteData
import com.autumnsun.birtutamhaber.utils.safeApiCall
import javax.inject.Inject

/*
 Created by Fatih Kurcenli on 12/4/2021
*/


class DetailRepository @Inject constructor(val newsApi: NewsApi) : DetailRepo {

    override suspend fun getOneNews(id: Int): RemoteData.Data.Haberler {
        val response = safeApiCall { newsApi.getOneNews(id) }
        if (response.isSuccessful) {
            Log.d("repo", response.body.toString())
        }
        return response.body
    }
}