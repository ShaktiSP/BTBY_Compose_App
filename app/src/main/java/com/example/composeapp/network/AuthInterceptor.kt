package com.example.composeapp.network

import android.util.Log
import com.example.composeapp.dataStore.preference.IPreferenceDataStoreAPI
import com.example.composeapp.dataStore.preference.PrefKeys
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(private val store: IPreferenceDataStoreAPI) :
    Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
        val authKey = runBlocking { store.getFirstPreference(PrefKeys.AUTH_KEY, "") }
        request.addHeader("Authorization", authKey)
        Log.d("TAG", "intercept: $authKey")
        return chain.proceed(request.build())
    }

}