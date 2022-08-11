package com.yogaprasetyo.testinternshipsistema

import android.app.Application
import com.yogaprasetyo.testinternshipsistema.core.di.networkModule
import com.yogaprasetyo.testinternshipsistema.core.di.repositoryModule
import com.yogaprasetyo.testinternshipsistema.di.viewModelModule
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            modules(
                listOf(
                    networkModule,
                    repositoryModule,
                    viewModelModule
                )
            )
        }
    }
}