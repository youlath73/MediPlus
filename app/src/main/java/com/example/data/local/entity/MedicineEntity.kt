package com.example.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.model.Medicine
import com.example.domain.model.MedicineCategory

@Entity(tableName = "medicines")
data class MedicineEntity(
  @PrimaryKey val id: Int,
  val brandName: String,
  val brandNameBn: String,
  val genericName: String,
  val genericNameBn: String,
  val companyName: String,
  val companyNameBn: String,
  val strength: String,
  val dosageForm: String,
  val dosageFormBn: String,
  val category: String,
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
  val isFavorite: Boolean = false,
  val lastViewedAt: Long = 0L,
) {
  fun toDomain(): Medicine =
    Medicine(
      id = id,
      brandName = brandName,
      brandNameBn = brandNameBn,
      genericName = genericName,
      genericNameBn = genericNameBn,
      companyName = companyName,
      companyNameBn = companyNameBn,
      strength = strength,
      dosageForm = dosageForm,
      dosageFormBn = dosageFormBn,
      category = MedicineCategory.fromKey(category),
      priceMrp = priceMrp,
      packUnit = packUnit,
      packUnitBn = packUnitBn,
      popularityScore = popularityScore,
      indicationsEn = indicationsEn,
      indicationsBn = indicationsBn,
      dosageEn = dosageEn,
      dosageBn = dosageBn,
      pharmacologyEn = pharmacologyEn,
      pharmacologyBn = pharmacologyBn,
      contraindicationsEn = contraindicationsEn,
      contraindicationsBn = contraindicationsBn,
      sideEffectsEn = sideEffectsEn,
      sideEffectsBn = sideEffectsBn,
      precautionsEn = precautionsEn,
      precautionsBn = precautionsBn,
      isFavorite = isFavorite,
      lastViewedAt = lastViewedAt,
    )
}
