@file:Suppress("DEPRECATION")

package com.shimmer.microcore.extension

import android.text.Html

fun String.fromHtml() = Html.fromHtml(this)

