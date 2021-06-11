@file:Suppress("DEPRECATION", "UNUSED")

package com.shimmer.microcore.utils

import android.content.Context
import android.os.Build
import android.os.LocaleList
import com.shimmer.microcore.core.context
import com.shimmer.microcore.storage.LanguageDatas
import com.shimmer.microcore.utils.LanguageUtil.Type.SIMPLIFIED_CHINESE
import com.shimmer.microcore.utils.LanguageUtil.Type.US
import java.util.*

object LanguageUtil {

    private var SystemType: Type

    private var currentType: Type

    init {
        SystemType = Type.getSystemLocalType(context().resources.configuration.locale)
        currentType = Type.valueOf(LanguageDatas.getLanguageType())
    }

    /**
     * 初始化界面语言
     */
    fun initLanguage(context: Context, block: () -> Unit = {}) {
        if (context.resources.configuration.locale != getLocale()) {
            applyChange(context)
            block()
        }
    }

    /**
     * 设置界面语言
     */
    fun setLanguage(context: Context, type: Type = US, block: () -> Unit = {}) {
        if (currentType != type) {
            currentType = type
            LanguageDatas.setLanguageType(currentType.name)

            applyChange(context)
            block()
        }
    }

    /**
     * 改变设置
     */
    private fun applyChange(context: Context) {
        context.resources.run {
            val locale = getLocale()
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                configuration.setLocale(locale)
                val localeList = LocaleList(locale)
                LocaleList.setDefault(localeList)
                configuration.setLocales(localeList)
            } else {
                configuration.locale = locale
            }

            updateConfiguration(configuration, displayMetrics)
        }

    }

    /**
     * 获取当前 app 设置的语言类型
     */
    fun getLocale() = when (currentType) {
        US -> Locale.US
        SIMPLIFIED_CHINESE -> Locale.SIMPLIFIED_CHINESE
    }

    /**
     * 获取系统语言类型
     * 默认 English
     */
    fun getSystemLocale() = SystemType.locale

    /**
     * 支持语言类型枚举
     */
    enum class Type(val locale: Locale) {
        /**
         * English
         */
        US(locale = Locale.US),

        /**
         * 简体中文
         */
        SIMPLIFIED_CHINESE(locale = Locale.SIMPLIFIED_CHINESE);

        companion object {
            /**
             * 获取系统语言类型
             * 默认 English
             */
            fun getSystemLocalType(locale: Locale) = when {
                (locale.language == US.locale.language)
                        && (locale.country == US.locale.country) -> US

                (locale.language == SIMPLIFIED_CHINESE.locale.language)
                        && (locale.country == SIMPLIFIED_CHINESE.locale.country) -> SIMPLIFIED_CHINESE
                else -> US
            }
        }
    }
}
