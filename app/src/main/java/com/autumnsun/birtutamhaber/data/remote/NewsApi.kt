package com.autumnsun.birtutamhaber.data.remote

import retrofit2.http.GET


/*
 Created by Fatih Kurcenli on 12/4/2021
*/



interface NewsApi {

    @GET("/news")
    suspend fun getNews():SimpleResponse
}