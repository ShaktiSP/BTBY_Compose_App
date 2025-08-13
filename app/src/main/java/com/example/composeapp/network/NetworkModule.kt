package com.example.composeapp.network

import android.content.Context
import com.example.composeapp.appUtils.AppConstants
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object ApiClient {
    private val gson = GsonBuilder()
        .setLenient()
        .create()

    @Provides
    @Singleton
    fun providesApiInterface(retrofitBuilder: Retrofit.Builder): ApiService {
        return retrofitBuilder.build().create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun providesRetrofitBuilder(okHttpClient: OkHttpClient): Retrofit.Builder = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create(gson))
        .baseUrl(AppConstants.BASE_URL)
        .client(okHttpClient)

    @Provides
    @Singleton
    fun providesOkHttpClient(authInterceptor: AuthInterceptor): OkHttpClient {
        return OkHttpClient.Builder().apply {
            addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            addNetworkInterceptor(authInterceptor)
        }.build()
    }

    @Provides
    @Singleton
    fun contextProvider(@ApplicationContext appContext: Context): Context {
        return appContext
    }


}
