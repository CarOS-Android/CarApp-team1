package com.thoughtworks.car.core.network.client

import android.content.Context
import com.thoughtworks.car.core.logging.Logger
import com.thoughtworks.car.core.network.interceptor.NetworkReachableInterceptor
import com.thoughtworks.car.core.utils.isDevEnvironment
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

open class HttpClient(private val context: Context) {
    var okHttpClient: OkHttpClient

    init {
        okHttpClient = OkHttpClient.Builder().apply {
            connectTimeout(CONNECT_TIME_OUT, TimeUnit.SECONDS)
            readTimeout(TIME_OUT, TimeUnit.SECONDS)
            writeTimeout(TIME_OUT, TimeUnit.SECONDS)
            initHttpClient(this)
        }.build()
    }

    private fun initHttpClient(builder: OkHttpClient.Builder) {
        addLoggingInterceptor(builder)
        addInterceptors(builder)
    }

    private fun addLoggingInterceptor(builder: OkHttpClient.Builder) {
        builder.takeIf { isDevEnvironment() }?.addInterceptor(
            HttpLoggingInterceptor(logger = AppHttpLogger()).apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
        )
    }

    private fun addInterceptors(builder: OkHttpClient.Builder) {
        builder.addInterceptor(NetworkReachableInterceptor(context))
    }

    private class AppHttpLogger : HttpLoggingInterceptor.Logger {
        override fun log(message: String) {
            Logger.d(message)
        }
    }

    companion object {
        const val TIME_OUT = 5L
        const val CONNECT_TIME_OUT = 15L
    }
}
