package com.example

import com.example.data.local.SeedData
import com.example.domain.model.AppLanguage
import com.example.domain.model.MedicineCategory
import com.example.localization.AppStringsProvider
import com.example.localization.formatTaka
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class ExampleUnitTest {
  @Test
  fun seedData_containsAllCategoriesAndSameGenericAlternatives() {
    val medicines = SeedData.medicines.map { it.toDomain() }
    assertTrue(medicines.size >= 20)
    assertTrue(medicines.any { it.category == MedicineCategory.ALLOPATHIC })
    assertTrue(medicines.any { it.category == MedicineCategory.HERBAL })
    assertTrue(medicines.any { it.category == MedicineCategory.VETERINARY })

    val paracetamolBrands = medicines.filter { it.genericName == "Paracetamol" }
    assertTrue(paracetamolBrands.size >= 3)
  }

  @Test
  fun localization_providesEnglishAndBanglaStrings() {
    val en = AppStringsProvider.get(AppLanguage.ENGLISH)
    val bn = AppStringsProvider.get(AppLanguage.BANGLA)

    assertEquals("MediPlus", en.appName)
    assertEquals("মেডিপ্লাস", bn.appName)
    assertEquals("৳1.20", formatTaka(1.2))
  }
}
