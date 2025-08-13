package com.example.composeapp.viewModel

import androidx.lifecycle.ViewModel
import com.example.composeapp.dataStore.preference.PrefKeys
import com.example.composeapp.dataStore.preference.PreferenceDataStoreModule
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class SessionViewModel @Inject constructor(private val preferenceDataStoreModule: PreferenceDataStoreModule) :
    ViewModel() {
    suspend fun getUserLoggedIn(): Flow<Boolean> {
        return preferenceDataStoreModule.getPreference(PrefKeys.IS_LOGIN, false)
    }

    suspend fun getUserLaunchAlready(): Flow<Boolean> {
        return preferenceDataStoreModule.getPreference(PrefKeys.IS_SECOND_TIME, true)
    }
}