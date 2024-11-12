package com.teixeira0x.squareclient.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

/** Check if the user has an internet connection. */
fun Context.hasNetworkConnection(): Boolean {
  val connectivityManager =
    getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
  val capabilities =
    connectivityManager.getNetworkCapabilities(
      connectivityManager.activeNetwork
    )
  return capabilities?.hasCapability(
    NetworkCapabilities.NET_CAPABILITY_INTERNET
  ) == true
}
