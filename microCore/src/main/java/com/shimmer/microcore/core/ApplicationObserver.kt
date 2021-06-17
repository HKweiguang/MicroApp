package com.shimmer.microcore.core

import android.content.Context
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.shimmer.microcore.utils.SystemUIUtil

class ApplicationObserver(private val context: Context) : LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume() {
        SystemUIUtil.hideSystemUI(context)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStop() {
        SystemUIUtil.showSystemUI(context)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestory() {
        SystemUIUtil.showSystemUI(context)
    }
}
