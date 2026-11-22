package com.example.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Sort
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.SearchOff
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.domain.model.AppLanguage
import com.example.domain.model.Medicine
import com.example.domain.model.MedicineCategory
import com.example.domain.model.SearchFilter
import com.example.domain.model.SortOption
import com.example.localization.UiStrings
import com.example.localization.formatTaka
import com.example.ui.components.EmptyStateCard
import com.example.ui.components.MedicineListCard
import com.example.ui.theme.JetBrainsMonoFamily
import kotlin.math.ceil

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun SearchScreen(
  query: String,
  filter: SearchFilter,
  sortOption: SortOption,
  medicines: List<Medicine>,
  availableCompanies: List<String>,
  availableStrengths: List<String>,
  availableForms: List<String>,
  maxCatalogPrice: Double,
  language: AppLanguage,
  strings: UiStrings,
  isExpandedWidth: Boolean,
  onQueryChange: (String) -> Unit,
  onSelectCategory: (MedicineCategory?) -> Unit,
  onUpdateFilter: (SearchFilter) -> Unit,
  onResetFilters: () -> Unit,
  onSortChange: (SortOption) -> Unit,
  onMedicineClick: (Int) -> Unit,
  onToggleFavorite: (Medicine) -> Unit,
  modifier: Modifier = Modifier,
) {
  var showSortMenu by remember { mutableStateOf(false) }
  var showFilterSheet by remember { mutableStateOf(false) }

  Column(
    modifier = modifier.fillMaxSize().testTag("search_screen"),
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    Column(
      modifier =
        Modifier.widthIn(max = 960.dp)
          .fillMaxWidth()
          .padding(horizontal = 16.dp, vertical = 12.dp),
      verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
      // Search Box
      OutlinedTextField(
        value = query,
        onValueChange = onQueryChange,
        modifier = Modifier.fillMaxWidth().testTag("directory_search_input"),
        placeholder = {
          Text(
            text = strings.searchFullHint,
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
          if (query.isNotEmpty()) {
            IconButton(
              onClick = { onQueryChange("") },
              modifier = Modifier.minimumInteractiveComponentSize().testTag("directory_clear_btn"),
            ) {
              Icon(imageVector = Icons.Filled.Close, contentDescription = strings.clearDesc)
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
      )

      // Quick Category Chips
      LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        item {
          FilterChip(
            selected = filter.category == null,
            onClick = { onSelectCategory(null) },
            label = { Text(strings.categoryAll) },
            modifier = Modifier.testTag("chip_cat_all"),
          )
        }
        items(MedicineCategory.entries) { cat ->
          FilterChip(
            selected = filter.category == cat,
            onClick = {
              onSelectCategory(if (filter.category == cat) null else cat)
            },
            label = { Text(strings.categoryName(cat)) },
            modifier = Modifier.testTag("chip_cat_${cat.storageKey.lowercase()}"),
          )
        }
      }

      // Count + Sort + Filter Controls
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
      ) {
        Text(
          text = strings.resultsFound(medicines.size),
          style = MaterialTheme.typography.labelLarge,
          color = MaterialTheme.colorScheme.onSurfaceVariant,
        )

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
          Box {
            Surface(
              onClick = { showSortMenu = true },
              shape = RoundedCornerShape(12.dp),
              color = MaterialTheme.colorScheme.surfaceVariant,
              modifier = Modifier.minimumInteractiveComponentSize().testTag("sort_button"),
            ) {
              Row(
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp),
              ) {
                Icon(
                  imageVector = Icons.AutoMirrored.Filled.Sort,
                  contentDescription = null,
                  modifier = Modifier.size(18.dp),
                )
                Text(
                  text = strings.sortName(sortOption),
                  style = MaterialTheme.typography.labelMedium,
                  maxLines = 1,
                )
              }
            }

            DropdownMenu(
              expanded = showSortMenu,
              onDismissRequest = { showSortMenu = false },
            ) {
              SortOption.entries.forEach { option ->
                DropdownMenuItem(
                  text = { Text(strings.sortName(option)) },
                  leadingIcon = {
                    if (sortOption == option) {
                      Icon(imageVector = Icons.Filled.Check, contentDescription = null)
                    }
                  },
                  onClick = {
                    onSortChange(option)
                    showSortMenu = false
                  },
                )
              }
            }
          }

          Surface(
            onClick = { showFilterSheet = true },
            shape = RoundedCornerShape(12.dp),
            color =
              if (filter.activeCount > 0) MaterialTheme.colorScheme.primaryContainer
              else MaterialTheme.colorScheme.surfaceVariant,
            modifier = Modifier.minimumInteractiveComponentSize().testTag("filter_button"),
          ) {
            Row(
              modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
              verticalAlignment = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.spacedBy(6.dp),
            ) {
              BadgedBox(
                badge = {
                  if (filter.activeCount > 0) {
                    Badge { Text("${filter.activeCount}") }
                  }
                }
              ) {
                Icon(
                  imageVector = Icons.Filled.FilterList,
                  contentDescription = null,
                  modifier = Modifier.size(18.dp),
                )
              }
              Text(
                text = strings.filterLabel,
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.SemiBold,
              )
            }
          }
        }
      }
    }

    // Results List / Grid
    if (medicines.isEmpty()) {
      Box(
        modifier = Modifier.widthIn(max = 700.dp).fillMaxWidth().padding(24.dp),
        contentAlignment = Alignment.Center,
      ) {
        EmptyStateCard(
          icon = Icons.Filled.SearchOff,
          title = strings.noSearchResultsTitle,
          description = strings.noSearchResultsDesc,
          actionLabel = strings.resetFilters,
          onAction = {
            onQueryChange("")
            onResetFilters()
          },
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
        items(medicines, key = { it.id }) { medicine ->
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

  // Multi-criteria Filter Modal Bottom Sheet
  if (showFilterSheet) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var draftFilter by remember(filter) { mutableStateOf(filter) }
    val sliderCap = ceil(maxCatalogPrice / 10.0) * 10.0

    ModalBottomSheet(
      onDismissRequest = { showFilterSheet = false },
      sheetState = sheetState,
    ) {
      Column(
        modifier =
          Modifier.fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp)
            .padding(bottom = 28.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
      ) {
        Text(
          text = strings.filtersSheetTitle,
          style = MaterialTheme.typography.titleLarge,
          fontWeight = FontWeight.Bold,
        )

        // Company Filter
        Text(
          text = strings.filterCompany,
          style = MaterialTheme.typography.titleSmall,
          color = MaterialTheme.colorScheme.primary,
        )
        FlowRow(
          horizontalArrangement = Arrangement.spacedBy(8.dp),
          verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
          FilterChip(
            selected = draftFilter.company == null,
            onClick = { draftFilter = draftFilter.copy(company = null) },
            label = { Text(strings.anyOption) },
          )
          availableCompanies.forEach { company ->
            FilterChip(
              selected = draftFilter.company == company,
              onClick = {
                draftFilter =
                  draftFilter.copy(
                    company = if (draftFilter.company == company) null else company
                  )
              },
              label = { Text(company, maxLines = 1, overflow = TextOverflow.Ellipsis) },
            )
          }
        }

        // Strength Filter
        Text(
          text = strings.filterStrength,
          style = MaterialTheme.typography.titleSmall,
          color = MaterialTheme.colorScheme.primary,
        )
        FlowRow(
          horizontalArrangement = Arrangement.spacedBy(8.dp),
          verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
          FilterChip(
            selected = draftFilter.strength == null,
            onClick = { draftFilter = draftFilter.copy(strength = null) },
            label = { Text(strings.anyOption) },
          )
          availableStrengths.forEach { strength ->
            FilterChip(
              selected = draftFilter.strength == strength,
              onClick = {
                draftFilter =
                  draftFilter.copy(
                    strength = if (draftFilter.strength == strength) null else strength
                  )
              },
              label = { Text(strength) },
            )
          }
        }

        // Dosage Form Filter
        Text(
          text = strings.filterForm,
          style = MaterialTheme.typography.titleSmall,
          color = MaterialTheme.colorScheme.primary,
        )
        FlowRow(
          horizontalArrangement = Arrangement.spacedBy(8.dp),
          verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
          FilterChip(
            selected = draftFilter.dosageForm == null,
            onClick = { draftFilter = draftFilter.copy(dosageForm = null) },
            label = { Text(strings.anyOption) },
          )
          availableForms.forEach { form ->
            FilterChip(
              selected = draftFilter.dosageForm == form,
              onClick = {
                draftFilter =
                  draftFilter.copy(
                    dosageForm = if (draftFilter.dosageForm == form) null else form
                  )
              },
              label = { Text(form) },
            )
          }
        }

        // Max Price Slider
        val currentPriceLimit = (draftFilter.maxPrice ?: sliderCap).toFloat()
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically,
        ) {
          Text(
            text = strings.filterMaxPrice,
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.primary,
          )
          Text(
            text =
              if (draftFilter.maxPrice == null) strings.anyOption
              else formatTaka(draftFilter.maxPrice!!),
            style =
              MaterialTheme.typography.labelLarge.copy(
                fontFamily = JetBrainsMonoFamily,
                fontWeight = FontWeight.Bold,
              ),
          )
        }
        Slider(
          value = currentPriceLimit,
          onValueChange = { value ->
            val rounded = kotlin.math.round(value.toDouble())
            draftFilter =
              draftFilter.copy(maxPrice = if (rounded >= sliderCap) null else rounded)
          },
          valueRange = 1f..sliderCap.toFloat().coerceAtLeast(10f),
        )

        Spacer(modifier = Modifier.height(4.dp))

        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.spacedBy(12.dp),
        ) {
          OutlinedButton(
            onClick = {
              draftFilter = SearchFilter()
              onResetFilters()
              showFilterSheet = false
            },
            modifier = Modifier.weight(1f).minimumInteractiveComponentSize(),
          ) {
            Text(strings.resetFilters)
          }
          Button(
            onClick = {
              onUpdateFilter(draftFilter)
              showFilterSheet = false
            },
            modifier = Modifier.weight(1f).minimumInteractiveComponentSize(),
          ) {
            Text(strings.applyFilters)
          }
        }
      }
    }
  }
}
