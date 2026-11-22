package com.example.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Bookmarks
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.domain.model.AppLanguage
import com.example.domain.model.Medicine
import com.example.localization.UiStrings
import com.example.ui.components.EmptyStateCard
import com.example.ui.components.LanguageAndThemeActions
import com.example.ui.components.MedicineListCard

@Composable
fun FavoritesScreen(
  favorites: List<Medicine>,
  language: AppLanguage,
  strings: UiStrings,
  isDarkTheme: Boolean,
  isExpandedWidth: Boolean,
  onMedicineClick: (Int) -> Unit,
  onToggleFavorite: (Medicine) -> Unit,
  onBrowseCatalog: () -> Unit,
  onToggleLanguage: () -> Unit,
  onToggleTheme: () -> Unit,
  modifier: Modifier = Modifier,
) {
  Column(
    modifier = modifier.fillMaxSize().testTag("favorites_screen"),
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    Row(
      modifier =
        Modifier.widthIn(max = 960.dp)
          .fillMaxWidth()
          .padding(horizontal = 16.dp, vertical = 14.dp),
      horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.CenterVertically,
    ) {
      Column(modifier = Modifier.weight(1f)) {
        Text(
          text = strings.favoritesTitle,
          style = MaterialTheme.typography.headlineSmall,
          fontWeight = FontWeight.Bold,
        )
        Text(
          text = strings.favoritesSubtitle(favorites.size),
          style = MaterialTheme.typography.bodySmall,
          color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
      }

      LanguageAndThemeActions(
        strings = strings,
        isDarkTheme = isDarkTheme,
        onToggleLanguage = onToggleLanguage,
        onToggleTheme = onToggleTheme,
      )
    }

    if (favorites.isEmpty()) {
      Box(
        modifier = Modifier.widthIn(max = 700.dp).fillMaxWidth().padding(24.dp),
        contentAlignment = Alignment.Center,
      ) {
        EmptyStateCard(
          icon = Icons.Outlined.Bookmarks,
          title = strings.noFavoritesTitle,
          description = strings.noFavoritesDesc,
          actionLabel = strings.browseMedicines,
          onAction = onBrowseCatalog,
        )
      }
    } else {
      LazyVerticalGrid(
        columns = GridCells.Fixed(if (isExpandedWidth) 2 else 1),
        modifier = Modifier.widthIn(max = 960.dp).fillMaxSize(),
        contentPadding = PaddingValues(start = 16.dp, end = 16.dp, bottom = 24.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
      ) {
        items(favorites, key = { it.id }) { medicine ->
          MedicineListCard(
            medicine = medicine,
            language = language,
            strings = strings,
            onClick = { onMedicineClick(medicine.id) },
            onToggleFavorite = { onToggleFavorite(medicine) },
          )
        }
      }
    }
  }
}
