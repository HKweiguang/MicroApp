package com.shimmer.microcore.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Model(
    val type: Boolean,
) : Parcelable
