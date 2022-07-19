package com.kilani.fizzbuzz.ui

import android.app.Application
import com.kilani.fizzbuzz.di.fizzBuzzModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class FizzBuzzApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@FizzBuzzApplication)
            modules(fizzBuzzModule)
        }
    }
}