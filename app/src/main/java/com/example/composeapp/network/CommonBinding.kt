package com.example.composeapp.network

import com.example.composeapp.dataStore.preference.IPreferenceDataStoreAPI
import com.example.composeapp.dataStore.preference.PreferenceDataStoreModule
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class CommonBinding {

    @Binds
    abstract fun bindPref(preferenceDataStoreModule: PreferenceDataStoreModule): IPreferenceDataStoreAPI

}