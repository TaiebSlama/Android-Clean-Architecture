package com.slama.cleanarchitecture.application

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

/**
 * Created by Slama Taieb (slama.taieb.contact@gmail.com)
 * Date : 10/1/2022
 * []
 */

class CleanArchitectureApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@CleanArchitectureApp)
            appModules
        }
    }
}