package com.autumnsun.birtutamhaber.data.remote

import com.autumnsun.birtutamhaber.data.remote.model.RemoteData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

/*
 Created by Fatih Kurcenli on 12/4/2021
*/

interface NewsApi {
    @GET("/.json")
    suspend fun getNews(): Response<RemoteData>

    /*suspend fun get*/

    @GET("/data/haberler/{id}.json")
    suspend fun getOneNews(@Path("id") id: Int): Response<RemoteData.Data.Haberler>
}