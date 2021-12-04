package com.autumnsun.birtutamhaber.ui.home

import com.autumnsun.birtutamhaber.data.remote.NewsApi
import com.autumnsun.birtutamhaber.data.remote.model.RemoteData
import com.autumnsun.birtutamhaber.utils.safeApiCall
import javax.inject.Inject

/*
 Created by Fatih Kurcenli on 12/4/2021
*/

class HomeRepository @Inject constructor(val newsApi: NewsApi) : NewsRepo {

    override suspend fun getNewsData(): List<RemoteData.Data.Haberler> {
        val response = safeApiCall { newsApi.getNews() }
        if (response.isSuccessful) {
            return response.body.data.haberler
        }
        //TODO check if empty list on viewModel
        return emptyList()
    }
}