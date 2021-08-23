package com.picpay.desafio.android.main.di.koin

import com.picpay.desafio.android.BuildConfig
import com.picpay.desafio.android.presentation.viewModel.ContactListViewModel
import com.picpay.desafio.android.domain.repository.RemoteRepository
import com.picpay.desafio.android.domain.repository.LocalRepository
import com.picpay.desafio.android.data.repository.local.datasource.LocalRepositoryImpl
import com.picpay.desafio.android.data.repository.local.db.DatabaseFactory
import com.picpay.desafio.android.data.repository.remote.datasource.RemoteRepositoryImpl
import com.picpay.desafio.android.data.repository.remote.service.PicPayService
import com.picpay.desafio.android.data.repository.remote.retrofit.OkHttpClientFactory
import com.picpay.desafio.android.data.repository.remote.retrofit.RetrofitClient
import com.picpay.desafio.android.domain.features.GetContactsFeature
import com.picpay.desafio.android.utils.ConnectionChecker
import okhttp3.Connection
import org.koin.dsl.module
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val allKoinModules by lazy {
    arrayListOf(
        jsonModule,
        networkModule,
        remoteRepositoryModule,
        localRepositoryModule,
        featuresModule,
        viewModelModule
    )
}

private val jsonModule = module {
    single { MoshiConverterFactory.create() }
}

private val networkModule = module {
    factory<Interceptor> { HttpLoggingInterceptor() }
    single { OkHttpClientFactory.build(get(), androidContext()) }
    single { RetrofitClient.build(BuildConfig.BASE_URL, get(), get()) }
    single { ConnectionChecker(androidContext())}
}

private val remoteRepositoryModule = module {
    single { get<Retrofit>().create(PicPayService::class.java) }
    factory<RemoteRepository> { RemoteRepositoryImpl(get(), get()) }
}

private val localRepositoryModule = module {
    single { DatabaseFactory.build(androidContext()) }
    factory<LocalRepository> { LocalRepositoryImpl(get()) }
}

private val featuresModule = module {
    factory { GetContactsFeature(get(),get()) }
}

private val viewModelModule = module {
    viewModel { ContactListViewModel(get()) }
}


