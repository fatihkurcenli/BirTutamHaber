package com.autumnsun.birtutamhaber.data.remote

import com.autumnsun.birtutamhaber.data.remote.model.RemoteData
import retrofit2.http.GET


/*
 Created by Fatih Kurcenli on 12/4/2021
*/



interface NewsApi {

    @GET("/.json")
    suspend fun getNews(): SimpleResponse<RemoteData>
}