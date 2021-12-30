package com.metaweather.android

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

class MetaWeatherApplication : Application() {

    companion object {
        const val TOKEN = ""

        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}
