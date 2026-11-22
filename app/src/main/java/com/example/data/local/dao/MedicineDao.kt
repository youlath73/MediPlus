package com.example.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.local.entity.MedicineEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MedicineDao {
  @Query("SELECT * FROM medicines ORDER BY popularityScore DESC, brandName ASC")
  fun observeAllMedicines(): Flow<List<MedicineEntity>>

  @Query("SELECT * FROM medicines WHERE isFavorite = 1 ORDER BY brandName ASC")
  fun observeFavorites(): Flow<List<MedicineEntity>>

  @Query("SELECT * FROM medicines WHERE lastViewedAt > 0 ORDER BY lastViewedAt DESC LIMIT 10")
  fun observeRecentlyViewed(): Flow<List<MedicineEntity>>

  @Query("SELECT * FROM medicines WHERE id = :id LIMIT 1")
  fun observeMedicineById(id: Int): Flow<MedicineEntity?>

  @Query("SELECT * FROM medicines WHERE genericName = :genericName AND id != :excludeId ORDER BY priceMrp ASC")
  fun observeSameGenericAlternatives(
    genericName: String,
    excludeId: Int,
  ): Flow<List<MedicineEntity>>

  @Query("SELECT COUNT(*) FROM medicines")
  suspend fun countMedicines(): Int

  @Insert(onConflict = OnConflictStrategy.IGNORE)
  suspend fun insertAll(medicines: List<MedicineEntity>)

  @Query("UPDATE medicines SET isFavorite = :isFavorite WHERE id = :id")
  suspend fun setFavorite(id: Int, isFavorite: Boolean)

  @Query("UPDATE medicines SET lastViewedAt = :timestamp WHERE id = :id")
  suspend fun updateLastViewed(id: Int, timestamp: Long)
}
