package com.shimmer.microcore.utils

import android.util.Log
import com.shimmer.microcore.BuildConfig

object LogUtil {

    private val TAG = BuildConfig.LOG_TAG

    fun i(msg: String = "i: ") {
        Log.i(TAG, msg)
    }

    fun d(msg: String = "d: ") {
        Log.d(TAG, msg)
    }

    fun e(msg: String = "e: ") {
        Log.e(TAG, msg)
    }

}
