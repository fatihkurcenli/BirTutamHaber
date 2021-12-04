package com.autumnsun.birtutamhaber.ui.detail

import com.autumnsun.birtutamhaber.data.remote.model.RemoteData

/*
 Created by Fatih Kurcenli on 12/4/2021
*/

interface DetailRepo {
    suspend fun getOneNews(id: Int): RemoteData.Data.Haberler
}