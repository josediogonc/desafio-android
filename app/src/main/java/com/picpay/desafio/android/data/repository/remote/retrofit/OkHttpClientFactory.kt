package com.picpay.desafio.android.data.repository.remote.retrofit

import android.content.Context
import com.picpay.desafio.android.BuildConfig
import com.picpay.desafio.android.utils.ConnectionChecker
import com.readystatesoftware.chuck.ChuckInterceptor
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

const val FIVE_MEGA_BYTES = 5 * 1024 * 1024

object OkHttpClientFactory {

    fun build(interceptor: Interceptor, context: Context): OkHttpClient {
        val cacheSize = FIVE_MEGA_BYTES.toLong()
        val myCache = Cache(context.cacheDir, cacheSize)

        val clientBuilder = OkHttpClient.Builder()
            .cache(myCache)
            .addInterceptor { chain ->
                val cacheControlValue = if (ConnectionChecker(context).hasInternetConnection()) "public, max-age=${5}" else "public, only-if-cached, max-stale=${60*2}"
                chain.proceed(chain
                    .request()
                    .newBuilder()
                    .header("Cache-Control", cacheControlValue)
                    .build())
            }
            .dispatcher(Dispatcher().apply { maxRequests = 1 })
            .readTimeout(1, TimeUnit.MINUTES)
            .connectTimeout(1, TimeUnit.MINUTES)
            .addInterceptor(interceptor)

        if (BuildConfig.DEBUG) {
            clientBuilder
                .addInterceptor(ChuckInterceptor(context))
                .addInterceptor(HttpLoggingInterceptor()
                    .apply { level = HttpLoggingInterceptor.Level.BODY }
                )
        }

        return clientBuilder.build()
    }


}