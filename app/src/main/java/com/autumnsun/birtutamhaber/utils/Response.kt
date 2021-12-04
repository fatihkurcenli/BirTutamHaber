package com.autumnsun.birtutamhaber.utils

import com.autumnsun.birtutamhaber.data.remote.SimpleResponse
import retrofit2.Response

/*
 Created by Fatih Kurcenli on 12/4/2021
*/

inline fun <T> safeApiCall(apiCall: () -> Response<T>): SimpleResponse<T> {
    return try {
        SimpleResponse.success(apiCall.invoke())
    } catch (e: Exception) {
        SimpleResponse.failure(e)
    }
}