package com.yhpgi.openwrtmonitor.domain.application

import android.app.Application

class OpenwrtMonitorApp : Application() {

    companion object {
        @get:Synchronized
        lateinit var appInitializer: OpenwrtMonitorApp
            private set
    }

    override fun onCreate() {
        super.onCreate()
        appInitializer = this
    }
}