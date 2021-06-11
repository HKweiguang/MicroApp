@file:Suppress("UNUSED")

package com.shimmer.microcore.utils

import android.content.Context
import android.content.Intent
import android.widget.Toast

object SystemUIUtil {

    private var isHideUI: Boolean = false

    fun init(context: Context) {
        hideSystemUI(context)
    }

    fun reverseSystemUI(context: Context) {
        if (!isHideUI) {
            hideSystemUI(context)
            Toast.makeText(context, "菜单已锁定", Toast.LENGTH_SHORT).show()
        } else {
            showSystemUI(context)
            Toast.makeText(context, "菜单已解锁", Toast.LENGTH_SHORT).show()
        }
    }

    fun hideSystemUI(context: Context) {
        context.run {
            sendBroadcast(Intent().apply {
                setAction("ACTION_SHOW_NAVBAR")
                putExtra("cmd", "hide")
            }, null)
            sendBroadcast(Intent().apply {
                setAction("ACTION_SHOW_STATUSBAR")
                putExtra("cmd", "hide")
            }, null)
        }
        isHideUI = true
    }

    fun showSystemUI(context: Context) {
        context.run {
            sendBroadcast(Intent().apply {
                setAction("ACTION_SHOW_NAVBAR")
                putExtra("cmd", "show")
            }, null)
            sendBroadcast(Intent().apply {
                setAction("ACTION_SHOW_STATUSBAR")
                putExtra("cmd", "show")
            }, null)
        }
        isHideUI = false
    }
}
