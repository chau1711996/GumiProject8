package com.example.gumiproject8

import android.app.Application
import com.example.gumiproject8.di.applicationModule
import org.koin.android.ext.android.startKoin

class AppGumi8: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(applicationModule))
    }
}