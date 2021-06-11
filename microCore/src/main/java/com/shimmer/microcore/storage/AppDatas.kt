package com.shimmer.microcore.storage

import com.shimmer.microcore.utils.SPUtil

object AppDatas {

    private val FIRST_IN = "first_in"

    fun getFirstIn() = SPUtil.getBoolean(FIRST_IN, true)

    fun setFirstIn(first: Boolean = false) = SPUtil.putBoolean(FIRST_IN, first)

}
