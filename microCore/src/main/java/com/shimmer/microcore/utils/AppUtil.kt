@file:Suppress("DEPRECATION", "UNUSED")

package com.shimmer.microcore.utils

import android.app.Activity
import com.shimmer.microcore.core.context
import com.shimmer.microcore.storage.AppDatas

object AppUtil {

    private var versionName: String = ""

    private var versionCode: Int = -1

    init {
        versionName = context().packageManager.getPackageInfo(context().packageName, 0).versionName
        versionCode = context().packageManager.getPackageInfo(context().packageName, 0).versionCode
    }

    fun getVersionName() = versionName

    fun getVersionCode() = versionCode

    fun isFirstIn() = AppDatas.getFirstIn()

    fun setNotFirstIn() = AppDatas.setFirstIn(false)

    /**
     * ---------------------------------------------------------------------------------------------
     */

    private val activities = mutableListOf<Activity>()

    fun addActivity(activity: Activity) {
        if (!activities.contains(activity)) {
            activities.add(activity)
        }
    }

    fun removeActivty(activity: Activity) {
        if (activities.contains(activity)) {
            activities.remove(activity)
        }
    }

    fun exitApp() {
        try {
            for (i in activities.indices.reversed()) {
                if (!activities[i].isFinishing) {
                    activities[i].finish()
                }
                activities.removeAt(i)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}
