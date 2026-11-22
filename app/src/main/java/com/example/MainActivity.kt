package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.data.local.MediPlusDatabase
import com.example.data.preferences.UserPreferencesRepository
import com.example.data.repository.MedicineRepository
import com.example.domain.model.ThemeMode
import com.example.ui.navigation.MediPlusApp
import com.example.ui.theme.MediPlusTheme
import com.example.ui.viewmodel.MediPlusViewModel

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()

    val database = MediPlusDatabase.getInstance(applicationContext)
    val medicineRepository = MedicineRepository(database.medicineDao())
    val preferencesRepository = UserPreferencesRepository(applicationContext)
    val viewModelFactory = MediPlusViewModel.Factory(medicineRepository, preferencesRepository)

    setContent {
      val viewModel: MediPlusViewModel = viewModel(factory = viewModelFactory)
      val language by viewModel.language.collectAsStateWithLifecycle()
      val themeMode by viewModel.themeMode.collectAsStateWithLifecycle()

      val isDarkTheme =
        when (themeMode) {
          ThemeMode.SYSTEM -> isSystemInDarkTheme()
          ThemeMode.LIGHT -> false
          ThemeMode.DARK -> true
        }

      MediPlusTheme(darkTheme = isDarkTheme, language = language) {
        MediPlusApp(viewModel = viewModel, isDarkTheme = isDarkTheme)
      }
    }
  }
}
