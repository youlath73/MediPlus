package com.example.ui.screens

import android.content.Intent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Apartment
import androidx.compose.material.icons.filled.Biotech
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.CompareArrows
import androidx.compose.material.icons.filled.DoNotDisturbOn
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.HealthAndSafety
import androidx.compose.material.icons.filled.MedicalInformation
import androidx.compose.material.icons.filled.Medication
import androidx.compose.material.icons.filled.ReportProblem
import androidx.compose.material.icons.filled.Science
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.WarningAmber
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material.icons.outlined.Translate
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.domain.model.AppLanguage
import com.example.domain.model.Medicine
import com.example.localization.UiStrings
import com.example.localization.formatTaka
import com.example.ui.components.CategoryPillBadge
import com.example.ui.theme.AlertRed
import com.example.ui.theme.JetBrainsMonoFamily
import com.example.ui.theme.SavingsGreen
import com.example.ui.theme.WarningAmber
import kotlin.math.abs
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MedicineDetailScreen(
  medicine: Medicine?,
  alternatives: List<Medicine>,
  language: AppLanguage,
  strings: UiStrings,
  onBack: () -> Unit,
  onToggleLanguage: () -> Unit,
  onToggleFavorite: (Medicine) -> Unit,
  onOpenMedicine: (Int) -> Unit,
  onSearchGeneric: (String) -> Unit,
  onRecordViewed: (Int) -> Unit,
  modifier: Modifier = Modifier,
) {
  val context = LocalContext.current
  val snackbarHostState = remember { SnackbarHostState() }
  val scope = rememberCoroutineScope()

  LaunchedEffect(medicine?.id) {
    medicine?.let { onRecordViewed(it.id) }
  }

  Scaffold(
    modifier = modifier.fillMaxSize().testTag("detail_screen"),
    snackbarHost = { SnackbarHost(snackbarHostState) },
    topBar = {
      TopAppBar(
        title = {
          Text(
            text = medicine?.localizedBrand(language) ?: strings.appName,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            fontWeight = FontWeight.Bold,
          )
        },
        navigationIcon = {
          IconButton(
            onClick = onBack,
            modifier = Modifier.minimumInteractiveComponentSize().testTag("detail_back_btn"),
          ) {
            Icon(
              imageVector = Icons.AutoMirrored.Filled.ArrowBack,
              contentDescription = strings.backDesc,
            )
          }
        },
        actions = {
          Surface(
            onClick = onToggleLanguage,
            shape = RoundedCornerShape(18.dp),
            color = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
            modifier = Modifier.minimumInteractiveComponentSize().testTag("detail_lang_toggle"),
          ) {
            Row(
              modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
              verticalAlignment = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.spacedBy(4.dp),
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

          if (medicine != null) {
            IconButton(
              onClick = {
                val willBeFavorite = !medicine.isFavorite
                onToggleFavorite(medicine)
                scope.launch {
                  snackbarHostState.showSnackbar(
                    if (willBeFavorite) strings.bookmarkAdded else strings.bookmarkRemoved
                  )
                }
              },
              modifier = Modifier.minimumInteractiveComponentSize().testTag("detail_bookmark_btn"),
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

            IconButton(
              onClick = {
                val shareText = strings.formatShareText(medicine, language)
                val intent =
                  Intent(Intent.ACTION_SEND).apply {
                    type = "text/plain"
                    putExtra(Intent.EXTRA_SUBJECT, medicine.localizedBrand(language))
                    putExtra(Intent.EXTRA_TEXT, shareText)
                  }
                context.startActivity(Intent.createChooser(intent, strings.shareMedicine))
              },
              modifier = Modifier.minimumInteractiveComponentSize().testTag("detail_share_btn"),
            ) {
              Icon(
                imageVector = Icons.Filled.Share,
                contentDescription = strings.shareMedicine,
              )
            }
          }
        },
        colors =
          TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.surface
          ),
      )
    },
  ) { innerPadding ->
    if (medicine == null) {
      Box(
        modifier = Modifier.fillMaxSize().padding(innerPadding),
        contentAlignment = Alignment.Center,
      ) {
        CircularProgressIndicator()
      }
      return@Scaffold
    }

    LazyColumn(
      modifier = Modifier.fillMaxSize().padding(innerPadding),
      contentPadding = PaddingValues(16.dp),
      verticalArrangement = Arrangement.spacedBy(14.dp),
      horizontalAlignment = Alignment.CenterHorizontally,
    ) {
      // Hero Clinical Header Card
      item {
        Card(
          modifier = Modifier.widthIn(max = 840.dp).fillMaxWidth(),
          shape = RoundedCornerShape(22.dp),
          colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
          border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
          elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        ) {
          Column(modifier = Modifier.fillMaxWidth()) {
            Box(
              modifier =
                Modifier.fillMaxWidth()
                  .background(
                    Brush.linearGradient(
                      colors = listOf(Color(0xFF0A3D82), Color(0xFF1565C0))
                    )
                  )
                  .padding(18.dp)
            ) {
              Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Row(
                  modifier = Modifier.fillMaxWidth(),
                  horizontalArrangement = Arrangement.SpaceBetween,
                  verticalAlignment = Alignment.CenterVertically,
                ) {
                  Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = Color.White.copy(alpha = 0.2f),
                  ) {
                    Text(
                      text = strings.categoryName(medicine.category),
                      style = MaterialTheme.typography.labelSmall,
                      color = Color.White,
                      fontWeight = FontWeight.Bold,
                      modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                    )
                  }
                  Surface(
                    shape = RoundedCornerShape(10.dp),
                    color = Color.White,
                  ) {
                    Column(
                      modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                      horizontalAlignment = Alignment.End,
                    ) {
                      Text(
                        text = formatTaka(medicine.priceMrp),
                        style =
                          MaterialTheme.typography.titleMedium.copy(
                            fontFamily = JetBrainsMonoFamily,
                            fontWeight = FontWeight.Bold,
                          ),
                        color = Color(0xFF0D47A1),
                      )
                      Text(
                        text = "${strings.mrpLabel} • ${medicine.localizedPackUnit(language)}",
                        style = MaterialTheme.typography.labelSmall,
                        color = Color(0xFF425468),
                      )
                    }
                  }
                }

                Text(
                  text = medicine.localizedBrand(language),
                  style = MaterialTheme.typography.headlineSmall,
                  color = Color.White,
                  fontWeight = FontWeight.Bold,
                )

                Surface(
                  onClick = { onSearchGeneric(medicine.genericName) },
                  shape = RoundedCornerShape(10.dp),
                  color = Color.White.copy(alpha = 0.14f),
                  modifier = Modifier.testTag("detail_generic_chip"),
                ) {
                  Row(
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                  ) {
                    Icon(
                      imageVector = Icons.Filled.Biotech,
                      contentDescription = null,
                      tint = Color.White,
                      modifier = Modifier.size(16.dp),
                    )
                    Text(
                      text = "${strings.genericLabel}: ${medicine.localizedGeneric(language)}",
                      style = MaterialTheme.typography.bodyMedium,
                      color = Color.White,
                      fontWeight = FontWeight.Medium,
                    )
                  }
                }
              }
            }

            Column(
              modifier = Modifier.fillMaxWidth().padding(16.dp),
              verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
              Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp),
              ) {
                Icon(
                  imageVector = Icons.Filled.Apartment,
                  contentDescription = null,
                  tint = MaterialTheme.colorScheme.primary,
                  modifier = Modifier.size(18.dp),
                )
                Column {
                  Text(
                    text = strings.companyLabel,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                  )
                  Text(
                    text = medicine.localizedCompany(language),
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.SemiBold,
                  )
                }
              }

              HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)

              Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp),
              ) {
                Icon(
                  imageVector = Icons.Filled.Medication,
                  contentDescription = null,
                  tint = MaterialTheme.colorScheme.primary,
                  modifier = Modifier.size(18.dp),
                )
                Column {
                  Text(
                    text = strings.strengthAndFormLabel,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                  )
                  Text(
                    text = "${medicine.strength} • ${medicine.localizedForm(language)}",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.SemiBold,
                  )
                }
              }
            }
          }
        }
      }

      // Expandable Clinical Monograph Cards
      item {
        ExpandableClinicalSectionCard(
          icon = Icons.Filled.MedicalInformation,
          title = strings.indicationsTitle,
          content = medicine.localizedIndications(language),
          accentColor = MaterialTheme.colorScheme.primary,
        )
      }
      item {
        ExpandableClinicalSectionCard(
          icon = Icons.Filled.HealthAndSafety,
          title = strings.dosageTitle,
          content = medicine.localizedDosage(language),
          accentColor = MaterialTheme.colorScheme.secondary,
        )
      }
      item {
        ExpandableClinicalSectionCard(
          icon = Icons.Filled.Science,
          title = strings.pharmacologyTitle,
          content = medicine.localizedPharmacology(language),
          accentColor = MaterialTheme.colorScheme.tertiary,
        )
      }
      item {
        ExpandableClinicalSectionCard(
          icon = Icons.Filled.DoNotDisturbOn,
          title = strings.contraindicationsTitle,
          content = medicine.localizedContraindications(language),
          accentColor = AlertRed,
        )
      }
      item {
        ExpandableClinicalSectionCard(
          icon = Icons.Filled.ReportProblem,
          title = strings.sideEffectsTitle,
          content = medicine.localizedSideEffects(language),
          accentColor = WarningAmber,
        )
      }
      item {
        ExpandableClinicalSectionCard(
          icon = Icons.Filled.WarningAmber,
          title = strings.precautionsTitle,
          content = medicine.localizedPrecautions(language),
          accentColor = WarningAmber,
        )
      }

      // Other Brands of Same Generic (Price Comparison)
      item {
        Row(
          modifier = Modifier.widthIn(max = 840.dp).fillMaxWidth().padding(top = 4.dp),
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
          Icon(
            imageVector = Icons.Filled.CompareArrows,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
          )
          Text(
            text = strings.otherBrandsTitle,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
          )
        }
      }

      if (alternatives.isEmpty()) {
        item {
          Card(
            modifier = Modifier.widthIn(max = 840.dp).fillMaxWidth(),
            shape = RoundedCornerShape(14.dp),
            colors =
              CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.45f)
              ),
          ) {
            Text(
              text = strings.noOtherBrands,
              style = MaterialTheme.typography.bodySmall,
              color = MaterialTheme.colorScheme.onSurfaceVariant,
              modifier = Modifier.padding(16.dp),
            )
          }
        }
      } else {
        items(alternatives, key = { "alt_${it.id}" }) { alt ->
          val diff = alt.priceMrp - medicine.priceMrp
          val comparisonLabel =
            when {
              abs(diff) < 0.01 -> strings.samePriceTag
              diff < 0.0 -> strings.cheaperByTag(formatTaka(abs(diff)))
              else -> strings.higherByTag(formatTaka(diff))
            }
          val badgeColor =
            when {
              abs(diff) < 0.01 -> MaterialTheme.colorScheme.primary
              diff < 0.0 -> SavingsGreen
              else -> WarningAmber
            }

          Card(
            modifier =
              Modifier.widthIn(max = 840.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(14.dp))
                .clickable { onOpenMedicine(alt.id) }
                .testTag("alt_brand_${alt.id}"),
            shape = RoundedCornerShape(14.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
          ) {
            Row(
              modifier = Modifier.fillMaxWidth().padding(14.dp),
              verticalAlignment = Alignment.CenterVertically,
            ) {
              Column(modifier = Modifier.weight(1f)) {
                Row(
                  verticalAlignment = Alignment.CenterVertically,
                  horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                  Text(
                    text = alt.localizedBrand(language),
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                  )
                  CategoryPillBadge(
                    category = alt.category,
                    label = alt.strength,
                  )
                }
                Spacer(modifier = Modifier.height(3.dp))
                Text(
                  text = "${alt.localizedCompany(language)} • ${alt.localizedForm(language)}",
                  style = MaterialTheme.typography.bodySmall,
                  color = MaterialTheme.colorScheme.onSurfaceVariant,
                  maxLines = 1,
                  overflow = TextOverflow.Ellipsis,
                )
              }

              Spacer(modifier = Modifier.width(8.dp))

              Column(horizontalAlignment = Alignment.End) {
                Text(
                  text = formatTaka(alt.priceMrp),
                  style =
                    MaterialTheme.typography.titleSmall.copy(
                      fontFamily = JetBrainsMonoFamily,
                      fontWeight = FontWeight.Bold,
                    ),
                  color = MaterialTheme.colorScheme.primary,
                )
                Surface(
                  shape = RoundedCornerShape(6.dp),
                  color = badgeColor.copy(alpha = 0.12f),
                ) {
                  Text(
                    text = comparisonLabel,
                    style = MaterialTheme.typography.labelSmall,
                    color = badgeColor,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
                  )
                }
              }

              Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant,
              )
            }
          }
        }
      }

      // Medical Disclaimer Footer
      item {
        Card(
          modifier = Modifier.widthIn(max = 840.dp).fillMaxWidth().padding(top = 6.dp),
          shape = RoundedCornerShape(14.dp),
          colors =
            CardDefaults.cardColors(
              containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f)
            ),
        ) {
          Text(
            text = strings.medicalDisclaimer,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(14.dp),
          )
        }
      }
    }
  }
}

@Composable
private fun ExpandableClinicalSectionCard(
  icon: ImageVector,
  title: String,
  content: String,
  accentColor: Color,
) {
  var expanded by rememberSaveable(title) { mutableStateOf(true) }

  Card(
    modifier =
      Modifier.widthIn(max = 840.dp)
        .fillMaxWidth()
        .clip(RoundedCornerShape(16.dp))
        .clickable { expanded = !expanded }
        .animateContentSize(),
    shape = RoundedCornerShape(16.dp),
    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
    border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
  ) {
    Column(
      modifier = Modifier.fillMaxWidth().padding(16.dp),
      verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
      Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
      ) {
        Row(
          modifier = Modifier.weight(1f),
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {
          Box(
            modifier =
              Modifier.size(34.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(accentColor.copy(alpha = 0.12f)),
            contentAlignment = Alignment.Center,
          ) {
            Icon(
              imageVector = icon,
              contentDescription = null,
              tint = accentColor,
              modifier = Modifier.size(18.dp),
            )
          }
          Text(
            text = title,
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface,
          )
        }
        Icon(
          imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
          contentDescription = null,
          tint = MaterialTheme.colorScheme.onSurfaceVariant,
        )
      }
      AnimatedVisibility(visible = expanded) {
        Text(
          text = content,
          style = MaterialTheme.typography.bodyMedium,
          color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
      }
    }
  }
}
