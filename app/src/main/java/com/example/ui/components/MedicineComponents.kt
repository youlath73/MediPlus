package com.example.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.LocalHospital
import androidx.compose.material.icons.filled.Medication
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material.icons.filled.Spa
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material.icons.outlined.Translate
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.domain.model.AppLanguage
import com.example.domain.model.Medicine
import com.example.domain.model.MedicineCategory
import com.example.localization.UiStrings
import com.example.localization.formatTaka
import com.example.ui.theme.CategoryAllopathicColor
import com.example.ui.theme.CategoryHerbalColor
import com.example.ui.theme.CategoryVeterinaryColor
import com.example.ui.theme.JetBrainsMonoFamily

@Composable
fun CategoryAccentColor(category: MedicineCategory): Color =
  when (category) {
    MedicineCategory.ALLOPATHIC -> CategoryAllopathicColor
    MedicineCategory.HERBAL -> CategoryHerbalColor
    MedicineCategory.VETERINARY -> CategoryVeterinaryColor
  }

fun categoryIcon(category: MedicineCategory): ImageVector =
  when (category) {
    MedicineCategory.ALLOPATHIC -> Icons.Filled.Medication
    MedicineCategory.HERBAL -> Icons.Filled.Spa
    MedicineCategory.VETERINARY -> Icons.Filled.Pets
  }

@Composable
fun LanguageAndThemeActions(
  strings: UiStrings,
  isDarkTheme: Boolean,
  onToggleLanguage: () -> Unit,
  onToggleTheme: () -> Unit,
  modifier: Modifier = Modifier,
) {
  Row(
    modifier = modifier,
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.spacedBy(4.dp),
  ) {
    Surface(
      onClick = onToggleLanguage,
      shape = RoundedCornerShape(20.dp),
      color = MaterialTheme.colorScheme.primaryContainer,
      contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
      modifier = Modifier.minimumInteractiveComponentSize().testTag("language_toggle_button"),
    ) {
      Row(
        modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(6.dp),
      ) {
        Icon(
          imageVector = Icons.Outlined.Translate,
          contentDescription = null,
          modifier = Modifier.size(16.dp),
        )
        Text(
          text = strings.languageToggleLabel,
          style = MaterialTheme.typography.labelLarge,
          fontWeight = FontWeight.Bold,
        )
      }
    }

    IconButton(
      onClick = onToggleTheme,
      modifier = Modifier.minimumInteractiveComponentSize().testTag("dark_mode_toggle_button"),
    ) {
      Icon(
        imageVector = if (isDarkTheme) Icons.Filled.LightMode else Icons.Filled.DarkMode,
        contentDescription = strings.darkModeToggleDesc,
        tint = MaterialTheme.colorScheme.onSurfaceVariant,
      )
    }
  }
}

@Composable
fun CategoryPillBadge(
  category: MedicineCategory,
  label: String,
  modifier: Modifier = Modifier,
) {
  val accent = CategoryAccentColor(category)
  Surface(
    modifier = modifier,
    shape = RoundedCornerShape(8.dp),
    color = accent.copy(alpha = 0.12f),
  ) {
    Row(
      modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.spacedBy(4.dp),
    ) {
      Icon(
        imageVector = categoryIcon(category),
        contentDescription = null,
        tint = accent,
        modifier = Modifier.size(13.dp),
      )
      Text(
        text = label,
        style = MaterialTheme.typography.labelSmall,
        color = accent,
        fontWeight = FontWeight.SemiBold,
      )
    }
  }
}

@Composable
fun PopularMedicineCard(
  medicine: Medicine,
  language: AppLanguage,
  strings: UiStrings,
  onClick: () -> Unit,
  onToggleFavorite: () -> Unit,
  modifier: Modifier = Modifier,
) {
  val accent = CategoryAccentColor(medicine.category)
  Card(
    modifier =
      modifier
        .width(214.dp)
        .clip(RoundedCornerShape(18.dp))
        .clickable(onClick = onClick)
        .testTag("popular_card_${medicine.id}"),
    shape = RoundedCornerShape(18.dp),
    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
    border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
  ) {
    Column(modifier = Modifier.padding(14.dp)) {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
      ) {
        Box(
          modifier =
            Modifier.size(38.dp)
              .clip(RoundedCornerShape(10.dp))
              .background(accent.copy(alpha = 0.12f)),
          contentAlignment = Alignment.Center,
        ) {
          Icon(
            imageVector = categoryIcon(medicine.category),
            contentDescription = null,
            tint = accent,
            modifier = Modifier.size(20.dp),
          )
        }
        IconButton(
          onClick = onToggleFavorite,
          modifier =
            Modifier.minimumInteractiveComponentSize().testTag("popular_fav_${medicine.id}"),
        ) {
          Icon(
            imageVector =
              if (medicine.isFavorite) Icons.Filled.Bookmark else Icons.Outlined.BookmarkBorder,
            contentDescription = strings.navFavorites,
            tint =
              if (medicine.isFavorite) MaterialTheme.colorScheme.primary
              else MaterialTheme.colorScheme.onSurfaceVariant,
          )
        }
      }

      Spacer(modifier = Modifier.height(8.dp))

      Text(
        text = medicine.localizedBrand(language),
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.Bold,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
      )
      Text(
        text = "${medicine.strength} • ${medicine.localizedForm(language)}",
        style = MaterialTheme.typography.labelMedium,
        color = MaterialTheme.colorScheme.primary,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
      )
      Spacer(modifier = Modifier.height(4.dp))
      Text(
        text = medicine.localizedGeneric(language),
        style = MaterialTheme.typography.bodySmall,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
      )
      Spacer(modifier = Modifier.height(2.dp))
      Text(
        text = medicine.localizedCompany(language),
        style = MaterialTheme.typography.labelSmall,
        color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.8f),
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
      )

      Spacer(modifier = Modifier.height(12.dp))

      Surface(
        shape = RoundedCornerShape(10.dp),
        color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.55f),
      ) {
        Row(
          modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp, vertical = 6.dp),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically,
        ) {
          Text(
            text = formatTaka(medicine.priceMrp),
            style =
              MaterialTheme.typography.titleSmall.copy(
                fontFamily = JetBrainsMonoFamily,
                fontWeight = FontWeight.Bold,
              ),
            color = MaterialTheme.colorScheme.primary,
          )
          Text(
            text = medicine.localizedPackUnit(language),
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
          )
        }
      }
    }
  }
}

@Composable
fun MedicineListCard(
  medicine: Medicine,
  language: AppLanguage,
  strings: UiStrings,
  onClick: () -> Unit,
  onToggleFavorite: () -> Unit,
  modifier: Modifier = Modifier,
) {
  val accent = CategoryAccentColor(medicine.category)
  Card(
    modifier =
      modifier
        .fillMaxWidth()
        .clip(RoundedCornerShape(16.dp))
        .clickable(onClick = onClick)
        .testTag("medicine_card_${medicine.id}"),
    shape = RoundedCornerShape(16.dp),
    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
    border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
  ) {
    Row(
      modifier = Modifier.fillMaxWidth().padding(14.dp),
      verticalAlignment = Alignment.CenterVertically,
    ) {
      Box(
        modifier =
          Modifier.size(48.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(accent.copy(alpha = 0.12f)),
        contentAlignment = Alignment.Center,
      ) {
        Icon(
          imageVector = categoryIcon(medicine.category),
          contentDescription = null,
          tint = accent,
          modifier = Modifier.size(24.dp),
        )
      }

      Spacer(modifier = Modifier.width(12.dp))

      Column(modifier = Modifier.weight(1f)) {
        Row(
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.spacedBy(6.dp),
        ) {
          Text(
            text = medicine.localizedBrand(language),
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
          )
          Surface(
            shape = RoundedCornerShape(6.dp),
            color = MaterialTheme.colorScheme.surfaceVariant,
          ) {
            Text(
              text = medicine.strength,
              style =
                MaterialTheme.typography.labelSmall.copy(fontFamily = JetBrainsMonoFamily),
              color = MaterialTheme.colorScheme.onSurfaceVariant,
              modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
            )
          }
        }

        Spacer(modifier = Modifier.height(2.dp))

        Text(
          text = "${medicine.localizedGeneric(language)} • ${medicine.localizedForm(language)}",
          style = MaterialTheme.typography.bodySmall,
          color = MaterialTheme.colorScheme.primary,
          fontWeight = FontWeight.Medium,
          maxLines = 1,
          overflow = TextOverflow.Ellipsis,
        )

        Spacer(modifier = Modifier.height(2.dp))

        Text(
          text = medicine.localizedCompany(language),
          style = MaterialTheme.typography.labelSmall,
          color = MaterialTheme.colorScheme.onSurfaceVariant,
          maxLines = 1,
          overflow = TextOverflow.Ellipsis,
        )
      }

      Spacer(modifier = Modifier.width(8.dp))

      Column(horizontalAlignment = Alignment.End) {
        Text(
          text = formatTaka(medicine.priceMrp),
          style =
            MaterialTheme.typography.titleSmall.copy(
              fontFamily = JetBrainsMonoFamily,
              fontWeight = FontWeight.Bold,
            ),
          color = MaterialTheme.colorScheme.primary,
        )
        Text(
          text = medicine.localizedPackUnit(language),
          style = MaterialTheme.typography.labelSmall,
          color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
        IconButton(
          onClick = onToggleFavorite,
          modifier = Modifier.minimumInteractiveComponentSize().testTag("fav_btn_${medicine.id}"),
        ) {
          Icon(
            imageVector =
              if (medicine.isFavorite) Icons.Filled.Bookmark else Icons.Outlined.BookmarkBorder,
            contentDescription = strings.navFavorites,
            tint =
              if (medicine.isFavorite) MaterialTheme.colorScheme.primary
              else MaterialTheme.colorScheme.onSurfaceVariant,
          )
        }
      }
    }
  }
}

@Composable
fun EmptyStateCard(
  icon: ImageVector,
  title: String,
  description: String,
  actionLabel: String? = null,
  onAction: (() -> Unit)? = null,
  modifier: Modifier = Modifier,
) {
  Card(
    modifier = modifier.fillMaxWidth(),
    shape = RoundedCornerShape(18.dp),
    colors =
      CardDefaults.cardColors(
        containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.45f)
      ),
    border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
  ) {
    Column(
      modifier = Modifier.fillMaxWidth().padding(24.dp),
      horizontalAlignment = Alignment.CenterHorizontally,
    ) {
      Box(
        modifier =
          Modifier.size(56.dp)
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.primaryContainer),
        contentAlignment = Alignment.Center,
      ) {
        Icon(
          imageVector = icon,
          contentDescription = null,
          tint = MaterialTheme.colorScheme.primary,
          modifier = Modifier.size(28.dp),
        )
      }
      Spacer(modifier = Modifier.height(12.dp))
      Text(
        text = title,
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.Bold,
      )
      Spacer(modifier = Modifier.height(4.dp))
      Text(
        text = description,
        style = MaterialTheme.typography.bodySmall,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
      )
      if (actionLabel != null && onAction != null) {
        Spacer(modifier = Modifier.height(14.dp))
        Surface(
          onClick = onAction,
          shape = RoundedCornerShape(12.dp),
          color = MaterialTheme.colorScheme.primary,
          contentColor = MaterialTheme.colorScheme.onPrimary,
          modifier = Modifier.minimumInteractiveComponentSize(),
        ) {
          Row(
            modifier = Modifier.padding(horizontal = 18.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
          ) {
            Icon(
              imageVector = Icons.Filled.LocalHospital,
              contentDescription = null,
              modifier = Modifier.size(18.dp),
            )
            Text(text = actionLabel, style = MaterialTheme.typography.labelLarge)
          }
        }
      }
    }
  }
}
