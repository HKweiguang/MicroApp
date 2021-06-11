@file:Suppress("UNUSED")

package com.shimmer.microcore.utils

import android.content.Context
import android.content.SharedPreferences
import com.shimmer.microcore.core.context
import com.shimmer.microcore.extension.spEdit

object SPUtil {
    private val SP_NAME: String = "sp_util"

    private val sp: SharedPreferences =
        context().getSharedPreferences(SP_NAME, Context.MODE_PRIVATE)

    fun getString(key: String, default: String = "") = sp.getString(key, default)!!

    fun getBoolean(key: String, default: Boolean = false) = sp.getBoolean(key, default)

    fun getFloat(key: String, default: Float = -1f) = sp.getFloat(key, default)

    fun getLong(key: String, default: Long = -1) = sp.getLong(key, default)

    fun getInt(key: String, default: Int = -1) = sp.getInt(key, default)

    fun putString(key: String, value: String) {
        sp.spEdit {
            it.putString(key, value)
        }
    }

    fun putBoolean(key: String, value: Boolean) {
        sp.spEdit {
            it.putBoolean(key, value)
        }
    }

    fun putFloat(key: String, value: Float) {
        sp.spEdit {
            it.putFloat(key, value)
        }
    }

    fun putLong(key: String, value: Long) {
        sp.spEdit {
            it.putLong(key, value)
        }
    }

    fun putInt(key: String, value: Int) {
        sp.spEdit {
            it.putInt(key, value)
        }
    }
}
