package com.picpay.desafio.android

import android.app.Application
import androidx.lifecycle.LifecycleObserver
import com.picpay.desafio.android.main.di.koin.allKoinModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApplication : Application(), LifecycleObserver {

    override fun onCreate() {
        super.onCreate()
        initServiceLocator()
    }

    private fun initServiceLocator() {
        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            modules(allKoinModules)
        }
    }

}