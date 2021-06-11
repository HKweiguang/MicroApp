package com.shimmer.microcore.core.net

import okhttp3.Interceptor
import okhttp3.Response

class HeaderParamsInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()

        val headers = request.headers.newBuilder()
            .add("Authorization", "")
            .add("timezone", "")
            .add("accept-language", "")
            .build()

        request = request.newBuilder().headers(headers).build()

        return chain.proceed(request)
    }
}
