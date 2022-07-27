package com.example.taghive

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.example.taghive.data.di.applicationModule
import com.example.taghive.data.di.networkModule
import com.example.taghive.data.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.core.context.startKoin

class TagHiveApplication:Application(), Application.ActivityLifecycleCallbacks {

    override fun onCreate() {
        super.onCreate()

        registerComponentCallbacks(this)
        startKoin {
            androidContext(this@TagHiveApplication)
            androidFileProperties()
            modules(listOf(applicationModule, viewModelModule, networkModule))
        }
    }

    override fun onActivityCreated(p0: Activity, p1: Bundle?) {
        TODO("Not yet implemented")
    }

    override fun onActivityStarted(p0: Activity) {
        TODO("Not yet implemented")
    }

    override fun onActivityResumed(p0: Activity) {
        TODO("Not yet implemented")
    }

    override fun onActivityPaused(p0: Activity) {
        TODO("Not yet implemented")
    }

    override fun onActivityStopped(p0: Activity) {
        TODO("Not yet implemented")
    }

    override fun onActivitySaveInstanceState(p0: Activity, p1: Bundle) {
        TODO("Not yet implemented")
    }

    override fun onActivityDestroyed(p0: Activity) {
        TODO("Not yet implemented")
    }
}