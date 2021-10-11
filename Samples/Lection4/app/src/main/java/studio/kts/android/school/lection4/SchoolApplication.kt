package studio.kts.android.school.lection4

import android.app.Application
import androidx.viewbinding.BuildConfig
import timber.log.Timber

class SchoolApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        if(BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

}
