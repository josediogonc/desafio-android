package com.picpay.desafio.android.data.repository.remote.retrofit

import android.content.Context
import com.picpay.desafio.android.BuildConfig
import com.readystatesoftware.chuck.ChuckInterceptor
import okhttp3.Dispatcher
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

object OkHttpClientFactory {

    fun build(interceptor: Interceptor, context: Context? = null): OkHttpClient {
        val clientBuilder = OkHttpClient.Builder()
            .dispatcher(Dispatcher().apply { maxRequests = 1 })
            .readTimeout(1, TimeUnit.MINUTES)
            .connectTimeout(1, TimeUnit.MINUTES)
            .addInterceptor(interceptor)

        if (BuildConfig.DEBUG) {
            context?.let { clientBuilder.addInterceptor(ChuckInterceptor(it)) }
            clientBuilder
                .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
        }

        return clientBuilder.build()
    }

}