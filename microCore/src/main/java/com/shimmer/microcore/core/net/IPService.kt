package com.shimmer.microcore.core.net

import com.shimmer.microcore.core.BaseBean
import io.reactivex.Observable
import retrofit2.http.*

interface IPService {

    @FormUrlEncoded
    @POST("your web interface")
    fun postWebInterface(
        @Field("field") field: String,
    ): Observable<BaseBean<Any>>

    @GET("your web interface")
    fun getWebInterface(
        @Query("query") query: String,
    ): Observable<BaseBean<Any>>
}
