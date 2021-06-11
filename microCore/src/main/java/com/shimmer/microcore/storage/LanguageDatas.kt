package com.shimmer.microcore.storage

import com.shimmer.microcore.BuildConfig
import com.shimmer.microcore.utils.LanguageUtil
import com.shimmer.microcore.utils.SPUtil

object LanguageDatas {

    private val LANGUAGE_TYPE = "language_type"

    fun getLanguageType() = SPUtil.getString(LANGUAGE_TYPE, default = defaultType)

    fun setLanguageType(type: String) = SPUtil.putString(LANGUAGE_TYPE, type)

    private var defaultType = when (BuildConfig.DEFAULT_LANGUAGE) {
        "en" -> LanguageUtil.Type.US.name
        "zh" -> LanguageUtil.Type.SIMPLIFIED_CHINESE.name
        else -> LanguageUtil.Type.US.name
    }
}
