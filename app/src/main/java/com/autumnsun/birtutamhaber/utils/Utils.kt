package com.autumnsun.birtutamhaber.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build

/*
 Created by Fatih Kurcenli on 12/4/2021
*/

fun isNetworkConnected(context: Context): Boolean {

    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        connectivityManager.activeNetwork
    } else {
        TODO("VERSION.SDK_INT < M")
    }
    val networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork)
    return networkCapabilities != null &&
            networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)

}