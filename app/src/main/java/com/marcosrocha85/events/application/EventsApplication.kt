package com.marcosrocha85.events.application

import android.app.Application
import android.content.Context

class EventsApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
    }

    companion object {
        lateinit var appContext: Context
            private set
    }
}