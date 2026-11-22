package com.example.domain.model

enum class MedicineCategory(val storageKey: String) {
  ALLOPATHIC("ALLOPATHIC"),
  HERBAL("HERBAL"),
  VETERINARY("VETERINARY");

  companion object {
    fun fromKey(key: String): MedicineCategory =
      entries.firstOrNull { it.storageKey == key } ?: ALLOPATHIC
  }
}

enum class SortOption {
  POPULARITY,
  PRICE_LOW_HIGH,
  PRICE_HIGH_LOW,
  NAME_ASC,
}

enum class AppLanguage(val code: String) {
  ENGLISH("en"),
  BANGLA("bn");

  companion object {
    fun fromCode(code: String): AppLanguage =
      entries.firstOrNull { it.code == code } ?: ENGLISH
  }
}

enum class ThemeMode(val key: String) {
  SYSTEM("system"),
  LIGHT("light"),
  DARK("dark");

  companion object {
    fun fromKey(key: String): ThemeMode =
      entries.firstOrNull { it.key == key } ?: SYSTEM
  }
}

data class SearchFilter(
  val category: MedicineCategory? = null,
  val company: String? = null,
  val strength: String? = null,
  val dosageForm: String? = null,
  val maxPrice: Double? = null,
) {
  val activeCount: Int
    get() =
      listOfNotNull(category, company, strength, dosageForm, maxPrice).size
}

data class Medicine(
  val id: Int,
  val brandName: String,
  val brandNameBn: String,
  val genericName: String,
  val genericNameBn: String,
  val companyName: String,
  val companyNameBn: String,
  val strength: String,
  val dosageForm: String,
  val dosageFormBn: String,
  val category: MedicineCategory,
  val priceMrp: Double,
  val packUnit: String,
  val packUnitBn: String,
  val popularityScore: Int,
  val indicationsEn: String,
  val indicationsBn: String,
  val dosageEn: String,
  val dosageBn: String,
  val pharmacologyEn: String,
  val pharmacologyBn: String,
  val contraindicationsEn: String,
  val contraindicationsBn: String,
  val sideEffectsEn: String,
  val sideEffectsBn: String,
  val precautionsEn: String,
  val precautionsBn: String,
  val isFavorite: Boolean,
  val lastViewedAt: Long,
) {
  fun localizedBrand(lang: AppLanguage): String =
    if (lang == AppLanguage.BANGLA) brandNameBn else brandName

  fun localizedGeneric(lang: AppLanguage): String =
    if (lang == AppLanguage.BANGLA) genericNameBn else genericName

  fun localizedCompany(lang: AppLanguage): String =
    if (lang == AppLanguage.BANGLA) companyNameBn else companyName

  fun localizedForm(lang: AppLanguage): String =
    if (lang == AppLanguage.BANGLA) dosageFormBn else dosageForm

  fun localizedPackUnit(lang: AppLanguage): String =
    if (lang == AppLanguage.BANGLA) packUnitBn else packUnit

  fun localizedIndications(lang: AppLanguage): String =
    if (lang == AppLanguage.BANGLA) indicationsBn else indicationsEn

  fun localizedDosage(lang: AppLanguage): String =
    if (lang == AppLanguage.BANGLA) dosageBn else dosageEn

  fun localizedPharmacology(lang: AppLanguage): String =
    if (lang == AppLanguage.BANGLA) pharmacologyBn else pharmacologyEn

  fun localizedContraindications(lang: AppLanguage): String =
    if (lang == AppLanguage.BANGLA) contraindicationsBn else contraindicationsEn

  fun localizedSideEffects(lang: AppLanguage): String =
    if (lang == AppLanguage.BANGLA) sideEffectsBn else sideEffectsEn

  fun localizedPrecautions(lang: AppLanguage): String =
    if (lang == AppLanguage.BANGLA) precautionsBn else precautionsEn
}
