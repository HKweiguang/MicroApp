package com.shimmer.microcore.bean

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class InstallMsg(
    val type: Boolean,
) : Parcelable
