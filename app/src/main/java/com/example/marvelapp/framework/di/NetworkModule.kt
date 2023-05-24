package com.example.marvelapp.framework.di

import com.example.marvelapp.BuildConfig
import com.example.marvelapp.framework.di.qualifier.BaseUrl
import com.example.marvelapp.framework.network.MarvelApi
import com.example.marvelapp.framework.network.interceptor.AuthorizationInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val TIMEOUT_SECONDS = 15L

    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        converterFactory: GsonConverterFactory,
        @BaseUrl baseUrl: String,
    ): MarvelApi = Retrofit.Builder().baseUrl(baseUrl)
        .client(okHttpClient)
        .addConverterFactory(converterFactory)
        .build()
        .create(MarvelApi::class.java)

    @Provides
    fun providerLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        setLevel(
            if (BuildConfig.DEBUG)
                HttpLoggingInterceptor.Level.BODY
            else HttpLoggingInterceptor.Level.NONE
        )
    }

    @Provides
    fun providerAuthorizationInterceptor(): AuthorizationInterceptor = AuthorizationInterceptor(
        BuildConfig.PUBLIC_KEY,
        BuildConfig.PRIVATE_KEY,
        Calendar.getInstance(TimeZone.getTimeZone("UTC"))
    )

    @Provides
    fun providerOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        authorizationInterceptor: AuthorizationInterceptor
    ): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(authorizationInterceptor)
            .readTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .connectTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .build()

    @Provides
    fun providerGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

}