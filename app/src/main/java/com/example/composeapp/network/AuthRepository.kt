package com.example.composeapp.network

import com.example.composeapp.GeneralResponse
import com.example.composeapp.appUtils.Resource
import com.example.composeapp.model.UpdateDeviceTokenReq
import com.example.composeapp.model.login.LoginResponse
import kotlinx.coroutines.flow.MutableSharedFlow
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepository @Inject constructor(
    private val api: ApiService,
) {
    private val loginResponse = MutableSharedFlow<Resource<LoginResponse?>>()
    val loginResponseShared: MutableSharedFlow<Resource<LoginResponse?>> = loginResponse

    private val updateDeviceVersion = MutableSharedFlow<Resource<GeneralResponse>>()
    val updateResponseShared: MutableSharedFlow<Resource<GeneralResponse>> = updateDeviceVersion

    ////// Login Function //////////////////
    suspend fun login(email: String, password: String) {
        loginResponse.emit(Resource.Loading())
        val response = runCatching {
            api.signIn(mapOf("email_id" to email, "password" to password))
        }
        if (response.isFailure) {
            if (response.exceptionOrNull()!! is IOException) {
                loginResponse.emit(Resource.InternetError())
            } else {
                loginResponse.emit(
                    Resource.Error(
                        response.exceptionOrNull()!!.localizedMessage ?: "Unknown error"
                    )
                )
            }
            return
        }
        response.getOrNull()?.let { data ->
            if (data.isSuccessful) {
                data.body()?.let {
                    loginResponse.emit(Resource.Success(it, "Login Successful", data.code()))
                } ?: loginResponse.emit(Resource.Error("Empty response", data.code()))
            } else {
                loginResponse.emit(Resource.Error("Error: ${data.message()}", data.code()))
            }
        } ?: loginResponse.emit(Resource.Error("Response Null"))
    }

    /////////////  Update Device //////////////

    suspend fun updateDeviceVersion(updateDeviceTokenRe: UpdateDeviceTokenReq) {
        updateDeviceVersion.emit(Resource.Loading())
        val response = runCatching {
            api.updateDeviceToken(updateDeviceTokenRe)
        }
        if (response.isFailure) {
            if (response.exceptionOrNull()!! is IOException) {
                updateDeviceVersion.emit(Resource.InternetError())
            } else {
                updateDeviceVersion.emit(
                    Resource.Error(
                        response.exceptionOrNull()!!.localizedMessage ?: "Unknown error"
                    )
                )
            }
            return
        }
        response.getOrNull()?.let { data ->
            if (data.isSuccessful) {
                data.body()?.let {
   //                 updateDeviceVersion.emit(Resource.Success(data.body()!!, data.message()))
                } ?: updateDeviceVersion.emit(Resource.Error("Empty response", data.code()))
            } else {
                updateDeviceVersion.emit(Resource.Error("Error: ${data.message()}", data.code()))
            }
        } ?: updateDeviceVersion.emit(Resource.Error("Response Null"))
    }

}