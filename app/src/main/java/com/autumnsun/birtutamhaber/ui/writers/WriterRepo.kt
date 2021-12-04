package com.autumnsun.birtutamhaber.ui.writers

import com.autumnsun.birtutamhaber.data.remote.model.RemoteData

/*
 Created by Fatih Kurcenli on 12/4/2021
*/

interface WriterRepo {
    suspend fun getWritersAll(): List<RemoteData.Data.Yazarlar>
}