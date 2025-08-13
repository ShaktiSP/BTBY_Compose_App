package com.example.composeapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composeapp.appUtils.Resource
import com.example.composeapp.dataStore.preference.IPreferenceDataStoreAPI
import com.example.composeapp.dataStore.preference.PrefKeys
import com.example.composeapp.model.UpdateDeviceTokenReq
import com.example.composeapp.network.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: AuthRepository,
    private val iPreferenceDataStoreAPI: IPreferenceDataStoreAPI
) : ViewModel() {

    val loginState = repository.loginResponseShared.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = Resource.None()
    )

    val updateVersionState = repository.updateResponseShared.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = Resource.None()
    )

    fun savePref(authKey: String) =
        viewModelScope.launch {
            iPreferenceDataStoreAPI.putPreference(PrefKeys.AUTH_KEY, authKey)
        }


    fun login(email: String, password: String) {
        viewModelScope.launch {
            repository.login(email, password)
        }
    }

    fun updateDevice(updateDeviceTokenReq: UpdateDeviceTokenReq){
        viewModelScope.launch {
            repository.updateDeviceVersion(updateDeviceTokenReq)
        }
    }


}