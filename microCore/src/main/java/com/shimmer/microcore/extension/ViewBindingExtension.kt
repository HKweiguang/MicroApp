package com.shimmer.microcore.extension

import android.app.Dialog
import android.content.Context
import android.view.View
import androidx.annotation.StyleRes
import androidx.viewbinding.ViewBinding
import com.shimmer.microcore.utils.DialogUtil

inline fun <T : ViewBinding> T.createDialog(
    context: Context,
    @StyleRes themeResId: Int = DialogUtil.DEFAULT_STYLE,
    dialogSetting: Dialog.() -> Unit = { DialogUtil.defaultDialogSetting(this) },
    view: T.(dialog: Dialog) -> View,
): Dialog {
    return Dialog(context, themeResId).run {
        dialogSetting()
        setContentView(view(this))
        this
    }
}
