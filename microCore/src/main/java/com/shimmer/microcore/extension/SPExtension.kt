package com.shimmer.microcore.extension

import android.content.SharedPreferences

inline fun SharedPreferences.spEdit(block: (SharedPreferences.Editor) -> Unit) {
    edit().run {
        block(this)
        commit()
    }
}
