package com.example.tokotekkotek.data

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
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
        val ID_USER = intPreferencesKey("id_user")
    }

    suspend fun saveUser(id : Int){
        context.counterDataStore.edit { preferences ->
            preferences[IS_LOGGED_IN] = true
            preferences[ID_USER] = id
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

    val idUser : Flow<Int> = context.counterDataStore.data.map {preferences ->
        preferences[ID_USER] ?: 0
    }

    fun isAlreadyLogin() : Boolean {
        return runBlocking {
            isLoggin().first()
        }
    }
}