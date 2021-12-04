package com.autumnsun.birtutamhaber.ui.home

import com.autumnsun.birtutamhaber.data.remote.model.RemoteData


/*
 Created by Fatih Kurcenli on 12/4/2021
*/



interface NewsRepo {
    suspend fun getNewsData(): List<RemoteData.Data.Haberler>
}