package com.example.data.repository

import com.example.data.local.SeedData
import com.example.data.local.dao.MedicineDao
import com.example.domain.model.Medicine
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MedicineRepository(private val medicineDao: MedicineDao) {
  val allMedicines: Flow<List<Medicine>> =
    medicineDao.observeAllMedicines().map { list -> list.map { it.toDomain() } }

  val favorites: Flow<List<Medicine>> =
    medicineDao.observeFavorites().map { list -> list.map { it.toDomain() } }

  val recentlyViewed: Flow<List<Medicine>> =
    medicineDao.observeRecentlyViewed().map { list -> list.map { it.toDomain() } }

  fun observeMedicineById(id: Int): Flow<Medicine?> =
    medicineDao.observeMedicineById(id).map { it?.toDomain() }

  fun observeAlternatives(genericName: String, excludeId: Int): Flow<List<Medicine>> =
    medicineDao.observeSameGenericAlternatives(genericName, excludeId).map { list ->
      list.map { it.toDomain() }
    }

  suspend fun ensureSeeded() {
    if (medicineDao.countMedicines() == 0) {
      medicineDao.insertAll(SeedData.medicines)
    }
  }

  suspend fun setFavorite(id: Int, isFavorite: Boolean) {
    medicineDao.setFavorite(id, isFavorite)
  }

  suspend fun recordViewed(id: Int) {
    medicineDao.updateLastViewed(id, System.currentTimeMillis())
  }
}
