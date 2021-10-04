package ru.iu3.sshchesnyak_kts_android_09_2021

import android.app.Application
import timber.log.Timber

class RedditApp: Application() {

    override fun onCreate() {
        super.onCreate()
        if(BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

}