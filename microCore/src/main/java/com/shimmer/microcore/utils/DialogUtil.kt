package com.shimmer.microcore.utils

import android.app.Dialog
import com.shimmer.microcore.R

object DialogUtil {

    val DEFAULT_STYLE = R.style.CustomDialog

    fun defaultDialogSetting(dialog: Dialog) {
        dialog.run {

            setCancelable(false)
        }
    }
}
