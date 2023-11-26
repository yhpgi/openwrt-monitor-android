package com.yhpgi.openwrtmonitor.domain.application

import android.app.Application
import com.tencent.mmkv.MMKV

class OpenwrtMonitorApp : Application() {

    companion object {
        @get:Synchronized
        lateinit var appInitializer: OpenwrtMonitorApp
            private set
    }

    override fun onCreate() {
        super.onCreate()
        appInitializer = this
        MMKV.initialize(this)
    }
}