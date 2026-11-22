package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.LocalPharmacy
import androidx.compose.material.icons.filled.OfflinePin
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.R
import com.example.domain.model.AppLanguage
import com.example.domain.model.Medicine
import com.example.domain.model.MedicineCategory
import com.example.localization.UiStrings
import com.example.localization.formatTaka
import com.example.ui.components.CategoryAccentColor
import com.example.ui.components.EmptyStateCard
import com.example.ui.components.LanguageAndThemeActions
import com.example.ui.components.MedicineListCard
import com.example.ui.components.PopularMedicineCard
import com.example.ui.components.categoryIcon
import com.example.ui.theme.JetBrainsMonoFamily

@Composable
fun HomeScreen(
  totalMedicineCount: Int,
  homeQuery: String,
  suggestions: List<Medicine>,
  popularMedicines: List<Medicine>,
  recentlyViewed: List<Medicine>,
  language: AppLanguage,
  strings: UiStrings,
  isDarkTheme: Boolean,
  onHomeQueryChange: (String) -> Unit,
  onSubmitSearch: (String) -> Unit,
  onCategoryClick: (MedicineCategory) -> Unit,
  onSeeAllPopular: () -> Unit,
  onMedicineClick: (Int) -> Unit,
  onToggleFavorite: (Medicine) -> Unit,
  onToggleLanguage: () -> Unit,
  onToggleTheme: () -> Unit,
  modifier: Modifier = Modifier,
) {
  val focusManager = LocalFocusManager.current

  LazyColumn(
    modifier = modifier.fillMaxSize().testTag("home_screen"),
    contentPadding = PaddingValues(bottom = 24.dp),
    verticalArrangement = Arrangement.spacedBy(18.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    // Top Brand Header
    item {
      Row(
        modifier =
          Modifier.widthIn(max = 900.dp)
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
      ) {
        Row(
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {
          Box(
            modifier =
              Modifier.size(42.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(MaterialTheme.colorScheme.primary),
            contentAlignment = Alignment.Center,
          ) {
            Icon(
              imageVector = Icons.Filled.LocalPharmacy,
              contentDescription = null,
              tint = MaterialTheme.colorScheme.onPrimary,
              modifier = Modifier.size(24.dp),
            )
          }
          Column {
            Text(
              text = strings.appName,
              style = MaterialTheme.typography.titleLarge,
              fontWeight = FontWeight.Bold,
              color = MaterialTheme.colorScheme.primary,
            )
            Text(
              text = strings.appTagline,
              style = MaterialTheme.typography.labelSmall,
              color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
          }
        }

        LanguageAndThemeActions(
          strings = strings,
          isDarkTheme = isDarkTheme,
          onToggleLanguage = onToggleLanguage,
          onToggleTheme = onToggleTheme,
        )
      }
    }

    // Clinical Hero Banner
    item {
      Box(modifier = Modifier.widthIn(max = 900.dp).fillMaxWidth().padding(horizontal = 16.dp)) {
        Card(
          shape = RoundedCornerShape(22.dp),
          elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
          modifier = Modifier.fillMaxWidth().height(152.dp),
        ) {
          Box(modifier = Modifier.fillMaxSize()) {
            Image(
              painter = painterResource(id = R.drawable.img_hero_medical),
              contentDescription = strings.appTagline,
              contentScale = ContentScale.Crop,
              modifier = Modifier.fillMaxSize(),
            )
            Box(
              modifier =
                Modifier.fillMaxSize()
                  .background(
                    Brush.horizontalGradient(
                      colors =
                        listOf(
                          Color(0xFF0A397A).copy(alpha = 0.92f),
                          Color(0xFF0D47A1).copy(alpha = 0.72f),
                          Color(0xFF0D47A1).copy(alpha = 0.18f),
                        )
                    )
                  )
            )
            Column(
              modifier = Modifier.fillMaxSize().padding(18.dp),
              verticalArrangement = Arrangement.SpaceBetween,
            ) {
              Surface(
                shape = RoundedCornerShape(20.dp),
                color = Color.White.copy(alpha = 0.18f),
              ) {
                Row(
                  modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                  verticalAlignment = Alignment.CenterVertically,
                  horizontalArrangement = Arrangement.spacedBy(5.dp),
                ) {
                  Icon(
                    imageVector = Icons.Filled.OfflinePin,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(14.dp),
                  )
                  Text(
                    text = strings.offlineReadyBadge,
                    style = MaterialTheme.typography.labelSmall,
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold,
                  )
                }
              }

              Column {
                Text(
                  text = strings.appTagline,
                  style = MaterialTheme.typography.titleLarge,
                  color = Color.White,
                  fontWeight = FontWeight.Bold,
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                  text = strings.catalogCountLabel(totalMedicineCount),
                  style = MaterialTheme.typography.bodySmall,
                  color = Color.White.copy(alpha = 0.90f),
                )
              }
            }
          }
        }
      }
    }

    // Smart Search Bar with Instant Suggestions
    item {
      Column(
        modifier = Modifier.widthIn(max = 900.dp).fillMaxWidth().padding(horizontal = 16.dp)
      ) {
        OutlinedTextField(
          value = homeQuery,
          onValueChange = onHomeQueryChange,
          modifier = Modifier.fillMaxWidth().testTag("home_search_input"),
          placeholder = {
            Text(
              text = strings.searchHint,
              maxLines = 1,
              overflow = TextOverflow.Ellipsis,
            )
          },
          leadingIcon = {
            Icon(
              imageVector = Icons.Filled.Search,
              contentDescription = null,
              tint = MaterialTheme.colorScheme.primary,
            )
          },
          trailingIcon = {
            if (homeQuery.isNotEmpty()) {
              IconButton(
                onClick = { onHomeQueryChange("") },
                modifier = Modifier.minimumInteractiveComponentSize().testTag("home_search_clear"),
              ) {
                Icon(
                  imageVector = Icons.Filled.Close,
                  contentDescription = strings.clearDesc,
                )
              }
            }
          },
          singleLine = true,
          shape = RoundedCornerShape(16.dp),
          colors =
            OutlinedTextFieldDefaults.colors(
              focusedContainerColor = MaterialTheme.colorScheme.surface,
              unfocusedContainerColor = MaterialTheme.colorScheme.surface,
              focusedBorderColor = MaterialTheme.colorScheme.primary,
              unfocusedBorderColor = MaterialTheme.colorScheme.outlineVariant,
            ),
          keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
          keyboardActions =
            KeyboardActions(
              onSearch = {
                focusManager.clearFocus()
                onSubmitSearch(homeQuery)
              }
            ),
        )

        AnimatedVisibility(visible = homeQuery.isNotBlank() && suggestions.isNotEmpty()) {
          Card(
            modifier = Modifier.fillMaxWidth().padding(top = 6.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
          ) {
            Column(modifier = Modifier.fillMaxWidth()) {
              Text(
                text = strings.suggestionsHeader,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp),
              )
              suggestions.forEachIndexed { index, med ->
                if (index > 0) {
                  HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
                }
                Row(
                  modifier =
                    Modifier.fillMaxWidth()
                      .clickable {
                        focusManager.clearFocus()
                        onMedicineClick(med.id)
                      }
                      .padding(horizontal = 14.dp, vertical = 10.dp)
                      .testTag("home_suggestion_${med.id}"),
                  verticalAlignment = Alignment.CenterVertically,
                  horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                  Column(modifier = Modifier.weight(1f)) {
                    Text(
                      text = "${med.localizedBrand(language)} (${med.strength})",
                      style = MaterialTheme.typography.titleSmall,
                      fontWeight = FontWeight.SemiBold,
                    )
                    Text(
                      text = "${med.localizedGeneric(language)} • ${med.localizedCompany(language)}",
                      style = MaterialTheme.typography.bodySmall,
                      color = MaterialTheme.colorScheme.onSurfaceVariant,
                      maxLines = 1,
                      overflow = TextOverflow.Ellipsis,
                    )
                  }
                  Spacer(modifier = Modifier.width(8.dp))
                  Text(
                    text = formatTaka(med.priceMrp),
                    style =
                      MaterialTheme.typography.labelLarge.copy(
                        fontFamily = JetBrainsMonoFamily,
                        fontWeight = FontWeight.Bold,
                      ),
                    color = MaterialTheme.colorScheme.primary,
                  )
                }
              }
            }
          }
        }
      }
    }

    // Categories Section (Allopathic, Herbal, Veterinary)
    item {
      Column(
        modifier = Modifier.widthIn(max = 900.dp).fillMaxWidth().padding(horizontal = 16.dp)
      ) {
        Text(
          text = strings.categoriesTitle,
          style = MaterialTheme.typography.titleMedium,
          fontWeight = FontWeight.Bold,
        )
        Spacer(modifier = Modifier.height(10.dp))
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {
          MedicineCategory.entries.forEach { category ->
            val accent = CategoryAccentColor(category)
            Card(
              modifier =
                Modifier.weight(1f)
                  .clip(RoundedCornerShape(16.dp))
                  .clickable { onCategoryClick(category) }
                  .testTag("category_card_${category.storageKey.lowercase()}"),
              shape = RoundedCornerShape(16.dp),
              colors =
                CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
              border = BorderStroke(1.dp, accent.copy(alpha = 0.25f)),
            ) {
              Column(
                modifier = Modifier.fillMaxWidth().padding(vertical = 14.dp, horizontal = 8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
              ) {
                Box(
                  modifier =
                    Modifier.size(42.dp).clip(CircleShape).background(accent.copy(alpha = 0.12f)),
                  contentAlignment = Alignment.Center,
                ) {
                  Icon(
                    imageVector = categoryIcon(category),
                    contentDescription = strings.categoryName(category),
                    tint = accent,
                    modifier = Modifier.size(22.dp),
                  )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                  text = strings.categoryName(category),
                  style = MaterialTheme.typography.labelMedium,
                  fontWeight = FontWeight.SemiBold,
                  maxLines = 1,
                  overflow = TextOverflow.Ellipsis,
                )
              }
            }
          }
        }
      }
    }

    // Popular Medicines Section
    item {
      Column(modifier = Modifier.widthIn(max = 900.dp).fillMaxWidth()) {
        Row(
          modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically,
        ) {
          Text(
            text = strings.popularMedicines,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
          )
          TextButton(
            onClick = onSeeAllPopular,
            modifier = Modifier.minimumInteractiveComponentSize().testTag("see_all_popular_btn"),
          ) {
            Text(text = strings.seeAll, style = MaterialTheme.typography.labelLarge)
            Spacer(modifier = Modifier.width(4.dp))
            Icon(
              imageVector = Icons.AutoMirrored.Filled.ArrowForward,
              contentDescription = null,
              modifier = Modifier.size(16.dp),
            )
          }
        }

        Spacer(modifier = Modifier.height(6.dp))

        LazyRow(
          contentPadding = PaddingValues(horizontal = 16.dp),
          horizontalArrangement = Arrangement.spacedBy(12.dp),
        ) {
          items(popularMedicines, key = { it.id }) { medicine ->
            PopularMedicineCard(
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

    // Recently Viewed Section
    item {
      Row(
        modifier = Modifier.widthIn(max = 900.dp).fillMaxWidth().padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
      ) {
        Icon(
          imageVector = Icons.Filled.History,
          contentDescription = null,
          tint = MaterialTheme.colorScheme.primary,
          modifier = Modifier.size(20.dp),
        )
        Text(
          text = strings.recentlyViewed,
          style = MaterialTheme.typography.titleMedium,
          fontWeight = FontWeight.Bold,
        )
      }
    }

    if (recentlyViewed.isEmpty()) {
      item {
        Box(
          modifier = Modifier.widthIn(max = 900.dp).fillMaxWidth().padding(horizontal = 16.dp)
        ) {
          EmptyStateCard(
            icon = Icons.Filled.History,
            title = strings.noRecentlyViewedTitle,
            description = strings.noRecentlyViewedDesc,
          )
        }
      }
    } else {
      items(recentlyViewed, key = { "recent_${it.id}" }) { medicine ->
        Box(
          modifier = Modifier.widthIn(max = 900.dp).fillMaxWidth().padding(horizontal = 16.dp)
        ) {
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
