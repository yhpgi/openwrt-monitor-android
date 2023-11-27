package com.yhpgi.openwrtmonitor.domain.helper.repository

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import com.yhpgi.openwrtmonitor.domain.helper.MainUtils
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

class DataStoreRepository(context: Context) {

    private val dataStore = context.dataStore

    val getThemeString: Flow<String> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                Log.d(MainUtils.KEY_APP_SETTINGS, exception.message.toString())
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            val theme =
                preferences[MainUtils.KEY_APP_THEME_STRING] ?: MainUtils.STRING_DEFAULT_THEME
            theme
        }

    suspend fun saveThemeString(setSelectedThemeString: String) {
        dataStore.edit { preferences ->
            preferences[MainUtils.KEY_APP_THEME_STRING] = setSelectedThemeString
        }
    }

    val getIPString: Flow<String> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                Log.d(MainUtils.KEY_APP_SETTINGS, exception.message.toString())
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            val storedIpAddress =
                preferences[MainUtils.KEY_APP_IP_STRING] ?: MainUtils.DEFAULT_IP
            storedIpAddress
        }

    suspend fun saveIPString(newIP: String) {
        dataStore.edit { preferences ->
            preferences[MainUtils.KEY_APP_IP_STRING] = newIP
        }
    }

    val getLuciPathString: Flow<String> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                Log.d(MainUtils.KEY_APP_SETTINGS, exception.message.toString())
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            val storedLuciPath =
                preferences[MainUtils.KEY_APP_LUCI_STRING] ?: MainUtils.DEFAULT_LUCI_PATH
            storedLuciPath
        }

    suspend fun saveLuciString(newLuciPath: String) {
        dataStore.edit { preferences ->
            preferences[MainUtils.KEY_APP_LUCI_STRING] = newLuciPath
        }
    }

    val getClashString: Flow<String> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                Log.d(MainUtils.KEY_APP_SETTINGS, exception.message.toString())
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            val storedOpenClashPath =
                preferences[MainUtils.KEY_APP_CLASH_STRING] ?: MainUtils.DEFAULT_CLASH_PATH
            storedOpenClashPath
        }

    suspend fun saveClashString(newOpenClashPath: String) {
        dataStore.edit { preferences ->
            preferences[MainUtils.KEY_APP_CLASH_STRING] = newOpenClashPath
        }
    }
}

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(MainUtils.KEY_APP_SETTINGS)
