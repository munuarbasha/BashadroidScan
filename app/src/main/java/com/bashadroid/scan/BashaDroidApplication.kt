package com.bashadroid.scan

import android.app.Application
import com.bashadroid.scan.BuildConfig

import timber.log.Timber

class BashaDroidApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
