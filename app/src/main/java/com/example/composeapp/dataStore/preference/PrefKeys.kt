package com.example.composeapp.dataStore.preference

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

class PrefKeys {
    companion object{
        val HAS_COOKIE = booleanPreferencesKey("has_cookie")
        val AVATAR = stringPreferencesKey("avatar")
        val DEVICE_TOKEN = stringPreferencesKey("device_token")
        val AUTH_KEY = stringPreferencesKey("auth_key")
        val IS_LOGIN = booleanPreferencesKey("is_login")
        val IS_SECOND_TIME = booleanPreferencesKey("is_second_time")
        val IS_PROFILE_SETUP = stringPreferencesKey("is_profile_setup")
        val IS_NOTIFICATION_ON = booleanPreferencesKey("is_notification_on")
    }
}