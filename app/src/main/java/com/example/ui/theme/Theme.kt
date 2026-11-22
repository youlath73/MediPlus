package com.example.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import com.example.domain.model.AppLanguage

private val MediPlusLightColorScheme =
  lightColorScheme(
    primary = MedBluePrimary,
    onPrimary = MedBlueOnPrimary,
    primaryContainer = MedBluePrimaryContainer,
    onPrimaryContainer = MedBlueOnPrimaryContainer,
    secondary = MedCyanSecondary,
    onSecondary = MedCyanOnSecondary,
    secondaryContainer = MedCyanSecondaryContainer,
    onSecondaryContainer = MedCyanOnSecondaryContainer,
    tertiary = MedTealTertiary,
    onTertiary = MedTealOnTertiary,
    tertiaryContainer = MedTealTertiaryContainer,
    onTertiaryContainer = MedTealOnTertiaryContainer,
    background = ClinicalBackgroundLight,
    onBackground = ClinicalOnBackgroundLight,
    surface = ClinicalSurfaceLight,
    onSurface = ClinicalOnSurfaceLight,
    surfaceVariant = ClinicalSurfaceVariantLight,
    onSurfaceVariant = ClinicalOnSurfaceVariantLight,
    outline = ClinicalOutlineLight,
    outlineVariant = ClinicalOutlineVariantLight,
  )

private val MediPlusDarkColorScheme =
  darkColorScheme(
    primary = MedBluePrimaryDark,
    onPrimary = MedBlueOnPrimaryDark,
    primaryContainer = MedBluePrimaryContainerDark,
    onPrimaryContainer = MedBlueOnPrimaryContainerDark,
    secondary = MedCyanSecondaryDark,
    onSecondary = MedCyanOnSecondaryDark,
    secondaryContainer = MedCyanSecondaryContainerDark,
    onSecondaryContainer = MedCyanOnSecondaryContainerDark,
    tertiary = MedTealTertiaryDark,
    onTertiary = MedTealOnTertiaryDark,
    tertiaryContainer = MedTealTertiaryContainerDark,
    onTertiaryContainer = MedTealOnTertiaryContainerDark,
    background = ClinicalBackgroundDark,
    onBackground = ClinicalOnBackgroundDark,
    surface = ClinicalSurfaceDark,
    onSurface = ClinicalOnSurfaceDark,
    surfaceVariant = ClinicalSurfaceVariantDark,
    onSurfaceVariant = ClinicalOnSurfaceVariantDark,
    outline = ClinicalOutlineDark,
    outlineVariant = ClinicalOutlineVariantDark,
  )

@Composable
fun MediPlusTheme(
  darkTheme: Boolean = isSystemInDarkTheme(),
  language: AppLanguage = AppLanguage.ENGLISH,
  content: @Composable () -> Unit,
) {
  val colorScheme = if (darkTheme) MediPlusDarkColorScheme else MediPlusLightColorScheme
  val typography = createMediPlusTypography(isBangla = language == AppLanguage.BANGLA)

  MaterialTheme(
    colorScheme = colorScheme,
    typography = typography,
    content = content,
  )
}
