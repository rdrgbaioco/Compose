package com.rdrgbaioco.compose

import android.app.Application
import com.rdrgbaioco.compose.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

/// Add this in AndroidManifest.xml
class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            // Log Koin into Android logger
            androidLogger()
            // Reference Android context
            androidContext(this@MainApplication)
            // Add modules injection
            modules(appModules)
        }
    }
}