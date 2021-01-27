package com.example.bliss.application

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BlissApplication : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}