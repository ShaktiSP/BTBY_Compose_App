package com.example.composeapp.network

import com.example.composeapp.GeneralResponse
import com.example.composeapp.model.UpdateDeviceTokenReq
import com.example.composeapp.model.login.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {

    @POST("sign-in-with-email")
    suspend fun signIn(@Body body: Map<String, String>): Response<LoginResponse>

    @POST("update-device-token")
    suspend fun updateDeviceToken(@Body updateDeviceTokenReq: UpdateDeviceTokenReq): Response<GeneralResponse>


}