package com.example.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.data.preferences.UserPreferencesRepository
import com.example.data.repository.MedicineRepository
import com.example.domain.model.AppLanguage
import com.example.domain.model.Medicine
import com.example.domain.model.MedicineCategory
import com.example.domain.model.SearchFilter
import com.example.domain.model.SortOption
import com.example.domain.model.ThemeMode
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MediPlusViewModel(
  private val repository: MedicineRepository,
  private val preferencesRepository: UserPreferencesRepository,
) : ViewModel() {

  init {
    viewModelScope.launch { repository.ensureSeeded() }
  }

  val language: StateFlow<AppLanguage> =
    preferencesRepository.languageFlow.stateIn(
      scope = viewModelScope,
      started = SharingStarted.WhileSubscribed(5_000),
      initialValue = AppLanguage.ENGLISH,
    )

  val themeMode: StateFlow<ThemeMode> =
    preferencesRepository.themeModeFlow.stateIn(
      scope = viewModelScope,
      started = SharingStarted.WhileSubscribed(5_000),
      initialValue = ThemeMode.SYSTEM,
    )

  val allMedicines: StateFlow<List<Medicine>> =
    repository.allMedicines.stateIn(
      scope = viewModelScope,
      started = SharingStarted.WhileSubscribed(5_000),
      initialValue = emptyList(),
    )

  val popularMedicines: StateFlow<List<Medicine>> =
    allMedicines
      .map { list -> list.sortedByDescending { it.popularityScore }.take(10) }
      .stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = emptyList(),
      )

  val favorites: StateFlow<List<Medicine>> =
    repository.favorites.stateIn(
      scope = viewModelScope,
      started = SharingStarted.WhileSubscribed(5_000),
      initialValue = emptyList(),
    )

  val recentlyViewed: StateFlow<List<Medicine>> =
    repository.recentlyViewed.stateIn(
      scope = viewModelScope,
      started = SharingStarted.WhileSubscribed(5_000),
      initialValue = emptyList(),
    )

  // Home smart search suggestions
  private val _homeQuery = MutableStateFlow("")
  val homeQuery: StateFlow<String> = _homeQuery.asStateFlow()

  val homeSuggestions: StateFlow<List<Medicine>> =
    combine(allMedicines, _homeQuery) { list, query ->
        val trimmed = query.trim()
        if (trimmed.isEmpty()) {
          emptyList()
        } else {
          list.filter { it.matchesQuery(trimmed) }.take(6)
        }
      }
      .stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = emptyList(),
      )

  // Search & Directory state
  private val _searchQuery = MutableStateFlow("")
  val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

  private val _searchFilter = MutableStateFlow(SearchFilter())
  val searchFilter: StateFlow<SearchFilter> = _searchFilter.asStateFlow()

  private val _sortOption = MutableStateFlow(SortOption.POPULARITY)
  val sortOption: StateFlow<SortOption> = _sortOption.asStateFlow()

  val availableCompanies: StateFlow<List<String>> =
    allMedicines
      .map { list -> list.map { it.companyName }.distinct().sorted() }
      .stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = emptyList(),
      )

  val availableStrengths: StateFlow<List<String>> =
    allMedicines
      .map { list -> list.map { it.strength }.distinct().sorted() }
      .stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = emptyList(),
      )

  val availableForms: StateFlow<List<String>> =
    allMedicines
      .map { list -> list.map { it.dosageForm }.distinct().sorted() }
      .stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = emptyList(),
      )

  val maxCatalogPrice: StateFlow<Double> =
    allMedicines
      .map { list -> list.maxOfOrNull { it.priceMrp } ?: 200.0 }
      .stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = 200.0,
      )

  val filteredMedicines: StateFlow<List<Medicine>> =
    combine(allMedicines, _searchQuery, _searchFilter, _sortOption) { list, query, filter, sort ->
        val trimmed = query.trim()
        list
          .asSequence()
          .filter { med -> trimmed.isEmpty() || med.matchesQuery(trimmed) }
          .filter { med -> filter.category == null || med.category == filter.category }
          .filter { med -> filter.company == null || med.companyName == filter.company }
          .filter { med -> filter.strength == null || med.strength == filter.strength }
          .filter { med -> filter.dosageForm == null || med.dosageForm == filter.dosageForm }
          .filter { med -> filter.maxPrice == null || med.priceMrp <= filter.maxPrice }
          .let { seq ->
            when (sort) {
              SortOption.POPULARITY -> seq.sortedByDescending { it.popularityScore }
              SortOption.PRICE_LOW_HIGH -> seq.sortedBy { it.priceMrp }
              SortOption.PRICE_HIGH_LOW -> seq.sortedByDescending { it.priceMrp }
              SortOption.NAME_ASC -> seq.sortedBy { it.brandName.lowercase() }
            }
          }
          .toList()
      }
      .stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = emptyList(),
      )

  fun observeMedicine(id: Int): Flow<Medicine?> = repository.observeMedicineById(id)

  fun observeAlternatives(genericName: String, excludeId: Int): Flow<List<Medicine>> =
    repository.observeAlternatives(genericName, excludeId)

  fun updateHomeQuery(query: String) {
    _homeQuery.value = query
  }

  fun updateSearchQuery(query: String) {
    _searchQuery.value = query
  }

  fun selectCategory(category: MedicineCategory?) {
    _searchFilter.value = _searchFilter.value.copy(category = category)
  }

  fun updateFilter(filter: SearchFilter) {
    _searchFilter.value = filter
  }

  fun resetFilters() {
    _searchFilter.value = SearchFilter()
  }

  fun updateSortOption(option: SortOption) {
    _sortOption.value = option
  }

  fun applySearchPreset(query: String = "", category: MedicineCategory? = null) {
    _searchQuery.value = query
    _searchFilter.value = SearchFilter(category = category)
  }

  fun toggleFavorite(medicine: Medicine) {
    viewModelScope.launch { repository.setFavorite(medicine.id, !medicine.isFavorite) }
  }

  fun recordViewed(medicineId: Int) {
    viewModelScope.launch { repository.recordViewed(medicineId) }
  }

  fun toggleLanguage() {
    viewModelScope.launch {
      val next =
        if (language.value == AppLanguage.ENGLISH) AppLanguage.BANGLA else AppLanguage.ENGLISH
      preferencesRepository.setLanguage(next)
    }
  }

  fun toggleDarkTheme(isCurrentlyDark: Boolean) {
    viewModelScope.launch {
      val next = if (isCurrentlyDark) ThemeMode.LIGHT else ThemeMode.DARK
      preferencesRepository.setThemeMode(next)
    }
  }

  private fun Medicine.matchesQuery(query: String): Boolean {
    val q = query.lowercase()
    return brandName.lowercase().contains(q) ||
      brandNameBn.contains(query) ||
      genericName.lowercase().contains(q) ||
      genericNameBn.contains(query) ||
      companyName.lowercase().contains(q) ||
      companyNameBn.contains(query) ||
      strength.lowercase().contains(q)
  }

  class Factory(
    private val repository: MedicineRepository,
    private val preferencesRepository: UserPreferencesRepository,
  ) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
      if (modelClass.isAssignableFrom(MediPlusViewModel::class.java)) {
        return MediPlusViewModel(repository, preferencesRepository) as T
      }
      throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
  }
}
