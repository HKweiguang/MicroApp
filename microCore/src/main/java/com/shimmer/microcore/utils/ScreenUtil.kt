@file:Suppress("DEPRECATION", "UNUSED")

package com.shimmer.microcore.utils

import android.content.Context
import android.view.WindowManager
import com.shimmer.microcore.core.context

object ScreenUtil {

    val screenWidth: Int
    val screenHeight: Int

    val density: Float

    init {
        (context().getSystemService(Context.WINDOW_SERVICE) as WindowManager).run {
            screenWidth = defaultDisplay.width
            screenHeight = defaultDisplay.height

            density = context().resources.displayMetrics.density
        }
    }

    fun Float.dp2px() = (this * density + 0.5f).toInt()

    fun Float.px2dp() = (this / density + 0.5f).toInt()

}
