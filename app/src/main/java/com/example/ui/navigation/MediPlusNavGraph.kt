package com.example.ui.navigation

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.localization.AppStringsProvider
import com.example.ui.screens.FavoritesScreen
import com.example.ui.screens.HomeScreen
import com.example.ui.screens.MedicineDetailScreen
import com.example.ui.screens.SearchScreen
import com.example.ui.viewmodel.MediPlusViewModel
import kotlinx.serialization.Serializable

@Serializable data object HomeRoute

@Serializable data object SearchRoute

@Serializable data object FavoritesRoute

@Serializable data class MedicineDetailRoute(val medicineId: Int)

private data class TopLevelDestination(
  val route: Any,
  val label: String,
  val selectedIcon: ImageVector,
  val unselectedIcon: ImageVector,
  val testTag: String,
  val badgeCount: Int = 0,
)

@Composable
fun MediPlusApp(
  viewModel: MediPlusViewModel,
  isDarkTheme: Boolean,
) {
  val navController = rememberNavController()
  val navBackStackEntry by navController.currentBackStackEntryAsState()
  val currentDestination = navBackStackEntry?.destination

  val language by viewModel.language.collectAsStateWithLifecycle()
  val strings = remember(language) { AppStringsProvider.get(language) }

  val allMedicines by viewModel.allMedicines.collectAsStateWithLifecycle()
  val popularMedicines by viewModel.popularMedicines.collectAsStateWithLifecycle()
  val recentlyViewed by viewModel.recentlyViewed.collectAsStateWithLifecycle()
  val favorites by viewModel.favorites.collectAsStateWithLifecycle()
  val homeQuery by viewModel.homeQuery.collectAsStateWithLifecycle()
  val homeSuggestions by viewModel.homeSuggestions.collectAsStateWithLifecycle()
  val searchQuery by viewModel.searchQuery.collectAsStateWithLifecycle()
  val searchFilter by viewModel.searchFilter.collectAsStateWithLifecycle()
  val sortOption by viewModel.sortOption.collectAsStateWithLifecycle()
  val filteredMedicines by viewModel.filteredMedicines.collectAsStateWithLifecycle()
  val availableCompanies by viewModel.availableCompanies.collectAsStateWithLifecycle()
  val availableStrengths by viewModel.availableStrengths.collectAsStateWithLifecycle()
  val availableForms by viewModel.availableForms.collectAsStateWithLifecycle()
  val maxCatalogPrice by viewModel.maxCatalogPrice.collectAsStateWithLifecycle()

  val isTopLevel =
    currentDestination?.hasRoute<HomeRoute>() == true ||
      currentDestination?.hasRoute<SearchRoute>() == true ||
      currentDestination?.hasRoute<FavoritesRoute>() == true

  val topDestinations =
    listOf(
      TopLevelDestination(
        route = HomeRoute,
        label = strings.navHome,
        selectedIcon = Icons.Filled.Home,
        unselectedIcon = Icons.Outlined.Home,
        testTag = "nav_home",
      ),
      TopLevelDestination(
        route = SearchRoute,
        label = strings.navSearch,
        selectedIcon = Icons.Filled.Search,
        unselectedIcon = Icons.Outlined.Search,
        testTag = "nav_search",
      ),
      TopLevelDestination(
        route = FavoritesRoute,
        label = strings.navFavorites,
        selectedIcon = Icons.Filled.Bookmark,
        unselectedIcon = Icons.Outlined.BookmarkBorder,
        testTag = "nav_favorites",
        badgeCount = favorites.size,
      ),
    )

  fun navigateTopLevel(route: Any) {
    navController.navigate(route) {
      popUpTo(navController.graph.findStartDestination().id) { saveState = true }
      launchSingleTop = true
      restoreState = true
    }
  }

  BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
    val isMediumOrExpanded = maxWidth >= 600.dp
    val isExpandedGrid = maxWidth >= 840.dp

    Row(modifier = Modifier.fillMaxSize()) {
      if (isMediumOrExpanded && isTopLevel) {
        NavigationRail(
          modifier = Modifier.fillMaxHeight().statusBarsPadding(),
          containerColor = MaterialTheme.colorScheme.surface,
        ) {
          Spacer(modifier = Modifier.weight(0.15f))
          topDestinations.forEach { dest ->
            val selected =
              when (dest.route) {
                is HomeRoute -> currentDestination?.hasRoute<HomeRoute>() == true
                is SearchRoute -> currentDestination?.hasRoute<SearchRoute>() == true
                is FavoritesRoute -> currentDestination?.hasRoute<FavoritesRoute>() == true
                else -> false
              }
            NavigationRailItem(
              selected = selected,
              onClick = { navigateTopLevel(dest.route) },
              modifier = Modifier.testTag(dest.testTag),
              icon = {
                BadgedBox(
                  badge = {
                    if (dest.badgeCount > 0) {
                      Badge { Text("${dest.badgeCount}") }
                    }
                  }
                ) {
                  Icon(
                    imageVector = if (selected) dest.selectedIcon else dest.unselectedIcon,
                    contentDescription = dest.label,
                  )
                }
              },
              label = { Text(dest.label) },
            )
          }
          Spacer(modifier = Modifier.weight(1f))
        }
      }

      Scaffold(
        modifier = Modifier.weight(1f).fillMaxHeight(),
        contentWindowInsets =
          if (isTopLevel) WindowInsets.safeDrawing else WindowInsets(0, 0, 0, 0),
        bottomBar = {
          if (!isMediumOrExpanded && isTopLevel) {
            NavigationBar(
              containerColor = MaterialTheme.colorScheme.surface,
              tonalElevation = 3.dp,
            ) {
              topDestinations.forEach { dest ->
                val selected =
                  when (dest.route) {
                    is HomeRoute -> currentDestination?.hasRoute<HomeRoute>() == true
                    is SearchRoute -> currentDestination?.hasRoute<SearchRoute>() == true
                    is FavoritesRoute -> currentDestination?.hasRoute<FavoritesRoute>() == true
                    else -> false
                  }
                NavigationBarItem(
                  selected = selected,
                  onClick = { navigateTopLevel(dest.route) },
                  modifier = Modifier.testTag(dest.testTag),
                  icon = {
                    BadgedBox(
                      badge = {
                        if (dest.badgeCount > 0) {
                          Badge { Text("${dest.badgeCount}") }
                        }
                      }
                    ) {
                      Icon(
                        imageVector = if (selected) dest.selectedIcon else dest.unselectedIcon,
                        contentDescription = dest.label,
                      )
                    }
                  },
                  label = { Text(dest.label) },
                )
              }
            }
          }
        },
      ) { innerPadding ->
        NavHost(
          navController = navController,
          startDestination = HomeRoute,
          modifier = Modifier.fillMaxSize().padding(innerPadding),
        ) {
          composable<HomeRoute> {
            HomeScreen(
              totalMedicineCount = allMedicines.size,
              homeQuery = homeQuery,
              suggestions = homeSuggestions,
              popularMedicines = popularMedicines,
              recentlyViewed = recentlyViewed,
              language = language,
              strings = strings,
              isDarkTheme = isDarkTheme,
              onHomeQueryChange = viewModel::updateHomeQuery,
              onSubmitSearch = { query ->
                viewModel.applySearchPreset(query = query, category = null)
                navigateTopLevel(SearchRoute)
              },
              onCategoryClick = { category ->
                viewModel.applySearchPreset(query = "", category = category)
                navigateTopLevel(SearchRoute)
              },
              onSeeAllPopular = {
                viewModel.applySearchPreset(query = "", category = null)
                navigateTopLevel(SearchRoute)
              },
              onMedicineClick = { id -> navController.navigate(MedicineDetailRoute(id)) },
              onToggleFavorite = viewModel::toggleFavorite,
              onToggleLanguage = viewModel::toggleLanguage,
              onToggleTheme = { viewModel.toggleDarkTheme(isDarkTheme) },
            )
          }

          composable<SearchRoute> {
            SearchScreen(
              query = searchQuery,
              filter = searchFilter,
              sortOption = sortOption,
              medicines = filteredMedicines,
              availableCompanies = availableCompanies,
              availableStrengths = availableStrengths,
              availableForms = availableForms,
              maxCatalogPrice = maxCatalogPrice,
              language = language,
              strings = strings,
              isExpandedWidth = isExpandedGrid,
              onQueryChange = viewModel::updateSearchQuery,
              onSelectCategory = viewModel::selectCategory,
              onUpdateFilter = viewModel::updateFilter,
              onResetFilters = viewModel::resetFilters,
              onSortChange = viewModel::updateSortOption,
              onMedicineClick = { id -> navController.navigate(MedicineDetailRoute(id)) },
              onToggleFavorite = viewModel::toggleFavorite,
            )
          }

          composable<FavoritesRoute> {
            FavoritesScreen(
              favorites = favorites,
              language = language,
              strings = strings,
              isDarkTheme = isDarkTheme,
              isExpandedWidth = isExpandedGrid,
              onMedicineClick = { id -> navController.navigate(MedicineDetailRoute(id)) },
              onToggleFavorite = viewModel::toggleFavorite,
              onBrowseCatalog = {
                viewModel.applySearchPreset(query = "", category = null)
                navigateTopLevel(SearchRoute)
              },
              onToggleLanguage = viewModel::toggleLanguage,
              onToggleTheme = { viewModel.toggleDarkTheme(isDarkTheme) },
            )
          }

          composable<MedicineDetailRoute> { backStackEntry ->
            val route = backStackEntry.toRoute<MedicineDetailRoute>()
            val medicine by
              remember(route.medicineId) { viewModel.observeMedicine(route.medicineId) }
                .collectAsStateWithLifecycle(initialValue = null)

            val genericName = medicine?.genericName.orEmpty()
            val alternatives by
              remember(genericName, route.medicineId) {
                if (genericName.isNotEmpty()) {
                  viewModel.observeAlternatives(genericName, route.medicineId)
                } else {
                  kotlinx.coroutines.flow.flowOf(emptyList())
                }
              }.collectAsStateWithLifecycle(initialValue = emptyList())

            MedicineDetailScreen(
              medicine = medicine,
              alternatives = alternatives,
              language = language,
              strings = strings,
              onBack = { navController.popBackStack() },
              onToggleLanguage = viewModel::toggleLanguage,
              onToggleFavorite = viewModel::toggleFavorite,
              onOpenMedicine = { id -> navController.navigate(MedicineDetailRoute(id)) },
              onSearchGeneric = { generic ->
                viewModel.applySearchPreset(query = generic, category = null)
                navigateTopLevel(SearchRoute)
              },
              onRecordViewed = viewModel::recordViewed,
            )
          }
        }
      }
    }
  }
}
