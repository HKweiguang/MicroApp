package com.shimmer.microcore.core.net

import com.shimmer.microcore.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object HttpUtil {

    private val BASE_URL = BuildConfig.HTTP_BASEURL

    val retrofit: Retrofit
    private val ipService: IPService

    init {
        retrofit = setRetrofit()
        ipService = setService()
    }

    private fun setRetrofit() = Retrofit
        .Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(setHttpClient())
        .build()

    private fun setHttpClient() = OkHttpClient
        .Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            if (BuildConfig.DEBUG) {
                setLevel(HttpLoggingInterceptor.Level.BODY)
            } else {
                setLevel(HttpLoggingInterceptor.Level.NONE)
            }
        })
        .addInterceptor(HeaderParamsInterceptor())
        .connectTimeout(10, TimeUnit.SECONDS)
        .readTimeout(10, TimeUnit.SECONDS)
        .build()

    private fun setService() = retrofit.create(IPService::class.java)

    fun getIPService() = ipService

}
