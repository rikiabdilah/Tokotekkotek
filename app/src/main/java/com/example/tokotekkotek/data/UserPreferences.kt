package com.example.tokotekkotek.data

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking

class UserPreferences (private val context: Context) {
    companion object {
        private val Context.counterDataStore by preferencesDataStore(
            name = "user_prefs"
        )
        val IS_LOGGED_IN = booleanPreferencesKey("is_logged_in")
    }

    suspend fun saveUser(){
        context.counterDataStore.edit { preferences ->
            preferences[IS_LOGGED_IN] = true
        }
    }
    suspend fun clear() {
        context.counterDataStore.edit { preferences ->
            preferences.clear()
        }
    }
    fun isLoggin() : Flow<Boolean>{
        return context.counterDataStore.data.map { preferences ->
            preferences[IS_LOGGED_IN] ?: false
        }
    }
    fun isAlreadyLogin() : Boolean {
        return runBlocking {
            isLoggin().first()
        }
    }
}