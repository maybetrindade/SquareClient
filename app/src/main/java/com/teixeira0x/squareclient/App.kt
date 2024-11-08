package com.teixeira0x.squareclient

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/** @author Felipe Teixeira */
@HiltAndroidApp
class App : Application() {

  companion object {
    val app by lazy { App() }
  }
}
