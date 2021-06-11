package com.shimmer.microcore.core

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.shimmer.microcore.utils.AppUtil
import com.shimmer.microcore.utils.LanguageUtil
import com.shimmer.microcore.utils.SystemUIUtil
import org.greenrobot.eventbus.EventBus

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppUtil.addActivity(this)

        initBase()
        setContentView(setView())
        init()
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this)
        }
        AppUtil.removeActivty(this)
    }

    private fun initBase() {
        LanguageUtil.initLanguage(this)
    }

    abstract fun init()

    abstract fun setView(): View

    fun exitApp() {
        SystemUIUtil.showSystemUI(this)
        AppUtil.exitApp()
    }

}
