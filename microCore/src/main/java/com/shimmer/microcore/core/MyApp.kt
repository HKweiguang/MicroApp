package com.shimmer.microcore.core

import android.app.Application
import androidx.lifecycle.ProcessLifecycleOwner
import com.shimmer.microcore.utils.UtilsController
import org.greenrobot.eventbus.EventBus

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()

        ProcessLifecycleOwner.get().lifecycle.addObserver(ApplicationObserver(this))

        initUtils()
        initThird()
    }

    private fun initThird() {
        EventBus.getDefault()
    }

    private fun initUtils() {
        UtilsController.init(this)
    }

}
