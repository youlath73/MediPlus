package com.example.localization

import com.example.domain.model.AppLanguage
import com.example.domain.model.Medicine
import com.example.domain.model.MedicineCategory
import com.example.domain.model.SortOption
import java.util.Locale

data class UiStrings(
  val appName: String,
  val appTagline: String,
  val navHome: String,
  val navSearch: String,
  val navFavorites: String,
  val searchHint: String,
  val searchFullHint: String,
  val offlineReadyBadge: String,
  val catalogCountLabel: (Int) -> String,
  val categoriesTitle: String,
  val categoryAll: String,
  val categoryAllopathic: String,
  val categoryHerbal: String,
  val categoryVeterinary: String,
  val popularMedicines: String,
  val seeAll: String,
  val recentlyViewed: String,
  val noRecentlyViewedTitle: String,
  val noRecentlyViewedDesc: String,
  val suggestionsHeader: String,
  val resultsFound: (Int) -> String,
  val sortLabel: String,
  val filterLabel: String,
  val sortPopularity: String,
  val sortPriceLow: String,
  val sortPriceHigh: String,
  val sortNameAsc: String,
  val filtersSheetTitle: String,
  val filterCompany: String,
  val filterStrength: String,
  val filterForm: String,
  val filterMaxPrice: String,
  val anyOption: String,
  val resetFilters: String,
  val applyFilters: String,
  val noSearchResultsTitle: String,
  val noSearchResultsDesc: String,
  val mrpLabel: String,
  val genericLabel: String,
  val companyLabel: String,
  val strengthAndFormLabel: String,
  val indicationsTitle: String,
  val dosageTitle: String,
  val pharmacologyTitle: String,
  val contraindicationsTitle: String,
  val sideEffectsTitle: String,
  val precautionsTitle: String,
  val otherBrandsTitle: String,
  val noOtherBrands: String,
  val samePriceTag: String,
  val cheaperByTag: (String) -> String,
  val higherByTag: (String) -> String,
  val shareMedicine: String,
  val bookmarkAdded: String,
  val bookmarkRemoved: String,
  val favoritesTitle: String,
  val favoritesSubtitle: (Int) -> String,
  val noFavoritesTitle: String,
  val noFavoritesDesc: String,
  val browseMedicines: String,
  val medicalDisclaimer: String,
  val darkModeToggleDesc: String,
  val languageToggleLabel: String,
  val backDesc: String,
  val clearDesc: String,
) {
  fun categoryName(category: MedicineCategory): String =
    when (category) {
      MedicineCategory.ALLOPATHIC -> categoryAllopathic
      MedicineCategory.HERBAL -> categoryHerbal
      MedicineCategory.VETERINARY -> categoryVeterinary
    }

  fun sortName(sort: SortOption): String =
    when (sort) {
      SortOption.POPULARITY -> sortPopularity
      SortOption.PRICE_LOW_HIGH -> sortPriceLow
      SortOption.PRICE_HIGH_LOW -> sortPriceHigh
      SortOption.NAME_ASC -> sortNameAsc
    }

  fun formatShareText(medicine: Medicine, lang: AppLanguage): String {
    val mrpFormatted = formatTaka(medicine.priceMrp)
    return buildString {
      appendLine("${medicine.localizedBrand(lang)} (${medicine.strength})")
      appendLine("$genericLabel: ${medicine.localizedGeneric(lang)}")
      appendLine("$companyLabel: ${medicine.localizedCompany(lang)}")
      appendLine("$mrpLabel: $mrpFormatted / ${medicine.localizedPackUnit(lang)}")
      appendLine()
      appendLine("$indicationsTitle:")
      appendLine(medicine.localizedIndications(lang))
      appendLine()
      appendLine("$dosageTitle:")
      appendLine(medicine.localizedDosage(lang))
      appendLine()
      append("— $appName BD")
    }
  }
}

fun formatTaka(amount: Double): String =
  String.format(Locale.US, "৳%.2f", amount)

object AppStringsProvider {
  private val english =
    UiStrings(
      appName = "MediPlus",
      appTagline = "Bangladesh Medicine Directory",
      navHome = "Home",
      navSearch = "Search",
      navFavorites = "Saved",
      searchHint = "Search brand, generic, or company...",
      searchFullHint = "Search Napa, Omeprazole, Square...",
      offlineReadyBadge = "Offline Ready",
      catalogCountLabel = { count -> "$count Medicines • BD MRP Index" },
      categoriesTitle = "Categories",
      categoryAll = "All",
      categoryAllopathic = "Allopathic",
      categoryHerbal = "Herbal",
      categoryVeterinary = "Veterinary",
      popularMedicines = "Popular Medicines",
      seeAll = "See all",
      recentlyViewed = "Recently Viewed",
      noRecentlyViewedTitle = "No recent views yet",
      noRecentlyViewedDesc = "Tap any medicine above to view clinical details and save your quick history.",
      suggestionsHeader = "Instant Matches",
      resultsFound = { count -> "$count medicines found" },
      sortLabel = "Sort",
      filterLabel = "Filter",
      sortPopularity = "Popularity",
      sortPriceLow = "Price: Low to High",
      sortPriceHigh = "Price: High to Low",
      sortNameAsc = "Name: A–Z",
      filtersSheetTitle = "Filter Medicines",
      filterCompany = "Company",
      filterStrength = "Strength",
      filterForm = "Dosage Form",
      filterMaxPrice = "Maximum Price (MRP)",
      anyOption = "Any",
      resetFilters = "Reset",
      applyFilters = "Apply Filters",
      noSearchResultsTitle = "No matching medicines",
      noSearchResultsDesc = "Try adjusting your search query or clearing active filters.",
      mrpLabel = "MRP",
      genericLabel = "Generic",
      companyLabel = "Manufacturer",
      strengthAndFormLabel = "Strength & Form",
      indicationsTitle = "Indications",
      dosageTitle = "Dosage & Administration",
      pharmacologyTitle = "Pharmacology",
      contraindicationsTitle = "Contraindications",
      sideEffectsTitle = "Side Effects",
      precautionsTitle = "Precautions & Warnings",
      otherBrandsTitle = "Other Brands of Same Generic",
      noOtherBrands = "No other brands listed for this generic in the local index.",
      samePriceTag = "Same MRP",
      cheaperByTag = { diff -> "Save $diff" },
      higherByTag = { diff -> "+$diff" },
      shareMedicine = "Share",
      bookmarkAdded = "Saved to Bookmarks",
      bookmarkRemoved = "Removed from Bookmarks",
      favoritesTitle = "Saved Medicines",
      favoritesSubtitle = { count -> "$count bookmarked for quick offline access" },
      noFavoritesTitle = "No saved medicines yet",
      noFavoritesDesc = "Bookmark frequently prescribed or household medicines to find them in one tap.",
      browseMedicines = "Browse Directory",
      medicalDisclaimer =
        "Medical Notice: Prices and clinical summaries are for informational reference in Bangladesh. Always consult a registered physician or pharmacist before taking any medication.",
      darkModeToggleDesc = "Toggle dark mode",
      languageToggleLabel = "বাংলা",
      backDesc = "Navigate back",
      clearDesc = "Clear search",
    )

  private val bangla =
    UiStrings(
      appName = "মেডিপ্লাস",
      appTagline = "বাংলাদেশ ওষুধ নির্দেশিকা",
      navHome = "হোম",
      navSearch = "অনুসন্ধান",
      navFavorites = "সংরক্ষিত",
      searchHint = "ব্র্যান্ড, জেনেরিক বা কোম্পানি খুঁজুন...",
      searchFullHint = "নাপা, ওমিপ্রাজল, স্কয়ার লিখুন...",
      offlineReadyBadge = "অফলাইন সক্রিয়",
      catalogCountLabel = { count -> "$count টি ওষুধ • বিডি এমআরপি তালিকা" },
      categoriesTitle = "ক্যাটাগরি",
      categoryAll = "সবগুলো",
      categoryAllopathic = "অ্যালোপ্যাথিক",
      categoryHerbal = "হার্বাল",
      categoryVeterinary = "ভেটেরিনারি",
      popularMedicines = "জনপ্রিয় ওষুধসমূহ",
      seeAll = "সব দেখুন",
      recentlyViewed = "সাম্প্রতিক দেখা",
      noRecentlyViewedTitle = "সাম্প্রতিক কোনো তথ্য নেই",
      noRecentlyViewedDesc = "যেকোনো ওষুধ খুললে এখানে দ্রুত দেখার ইতিহাস সংরক্ষিত হবে।",
      suggestionsHeader = "দ্রুত ফলাফল",
      resultsFound = { count -> "$count টি ওষুধ পাওয়া গেছে" },
      sortLabel = "সাজান",
      filterLabel = "ফিল্টার",
      sortPopularity = "জনপ্রিয়তা",
      sortPriceLow = "দাম: কম থেকে বেশি",
      sortPriceHigh = "দাম: বেশি থেকে কম",
      sortNameAsc = "নাম: A–Z",
      filtersSheetTitle = "ওষুধ ফিল্টার করুন",
      filterCompany = "কোম্পানি",
      filterStrength = "মাত্রা (Strength)",
      filterForm = "ডোজের ধরন",
      filterMaxPrice = "সর্বোচ্চ খুচরা মূল্য (MRP)",
      anyOption = "সব",
      resetFilters = "রিসেট",
      applyFilters = "ফিল্টার প্রয়োগ করুন",
      noSearchResultsTitle = "কোনো ওষুধ পাওয়া যায়নি",
      noSearchResultsDesc = "অনুসন্ধানের শব্দ পরিবর্তন করুন অথবা ফিল্টার রিসেট করুন।",
      mrpLabel = "খুচরা মূল্য (MRP)",
      genericLabel = "জেনেরিক নাম",
      companyLabel = "প্রস্তুতকারক কোম্পানি",
      strengthAndFormLabel = "মাত্রা ও ধরন",
      indicationsTitle = "নির্দেশনা (Indications)",
      dosageTitle = "মাত্রা ও ব্যবহারবিধি",
      pharmacologyTitle = "ফার্মাকোলজি",
      contraindicationsTitle = "প্রতিনির্দেশনা",
      sideEffectsTitle = "পার্শ্বপ্রতিক্রিয়া",
      precautionsTitle = "সতর্কতা ও সাবধানতা",
      otherBrandsTitle = "একই জেনেরিকের অন্যান্য ব্র্যান্ড",
      noOtherBrands = "স্থানীয় তালিকায় এই জেনেরিকের অন্য কোনো ব্র্যান্ড নেই।",
      samePriceTag = "একই মূল্য",
      cheaperByTag = { diff -> "$diff সাশ্রয়" },
      higherByTag = { diff -> "+$diff" },
      shareMedicine = "শেয়ার",
      bookmarkAdded = "সংরক্ষিত তালিকায় যুক্ত হয়েছে",
      bookmarkRemoved = "সংরক্ষিত তালিকা থেকে সরানো হয়েছে",
      favoritesTitle = "সংরক্ষিত ওষুধ",
      favoritesSubtitle = { count -> "অফলাইনে দ্রুত দেখার জন্য $count টি সংরক্ষিত" },
      noFavoritesTitle = "কোনো ওষুধ সংরক্ষিত নেই",
      noFavoritesDesc = "প্রয়োজনীয় ওষুধগুলো বুকমার্ক করে রাখলে অফলাইনেও এক ট্যাপেই দেখতে পারবেন।",
      browseMedicines = "ওষুধ দেখুন",
      medicalDisclaimer =
        "সতর্কবার্তা: মূল্য ও ক্লিনিক্যাল তথ্য শুধুমাত্র প্রাথমিক ধারণার জন্য। যেকোনো ওষুধ সেবনের পূর্বে রেজিস্টার্ড চিকিৎসকের পরামর্শ নিন।",
      darkModeToggleDesc = "ডার্ক মোড পরিবর্তন করুন",
      languageToggleLabel = "EN",
      backDesc = "পিছনে যান",
      clearDesc = "পরিষ্কার করুন",
    )

  fun get(language: AppLanguage): UiStrings =
    when (language) {
      AppLanguage.ENGLISH -> english
      AppLanguage.BANGLA -> bangla
    }
}
