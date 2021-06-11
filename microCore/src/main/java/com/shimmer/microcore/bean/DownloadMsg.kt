package com.shimmer.microcore.bean

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DownloadMsg(
    val type: Boolean,
) : Parcelable
