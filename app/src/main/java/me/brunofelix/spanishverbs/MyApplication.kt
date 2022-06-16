package me.brunofelix.spanishverbs

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import timber.log.Timber
import timber.log.Timber.DebugTree

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        }
    }
}