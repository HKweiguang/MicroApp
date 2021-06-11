package com.shimmer.microcore.core

import androidx.annotation.Keep

@Keep
data class BaseBean<T>(
    val result: Int,
    val message: String,
    val data: T,
)
