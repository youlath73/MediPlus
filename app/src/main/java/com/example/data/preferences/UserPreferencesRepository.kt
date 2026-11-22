package com.example.data.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.domain.model.AppLanguage
import com.example.domain.model.ThemeMode
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.userPrefsDataStore: DataStore<Preferences> by
  preferencesDataStore(name = "mediplus_user_prefs")

class UserPreferencesRepository(private val context: Context) {
  private object Keys {
    val LANGUAGE = stringPreferencesKey("app_language")
    val THEME_MODE = stringPreferencesKey("theme_mode")
  }

  val languageFlow: Flow<AppLanguage> =
    context.userPrefsDataStore.data.map { prefs ->
      AppLanguage.fromCode(prefs[Keys.LANGUAGE] ?: AppLanguage.ENGLISH.code)
    }

  val themeModeFlow: Flow<ThemeMode> =
    context.userPrefsDataStore.data.map { prefs ->
      ThemeMode.fromKey(prefs[Keys.THEME_MODE] ?: ThemeMode.SYSTEM.key)
    }

  suspend fun setLanguage(language: AppLanguage) {
    context.userPrefsDataStore.edit { prefs -> prefs[Keys.LANGUAGE] = language.code }
  }

  suspend fun setThemeMode(themeMode: ThemeMode) {
    context.userPrefsDataStore.edit { prefs -> prefs[Keys.THEME_MODE] = themeMode.key }
  }
}
