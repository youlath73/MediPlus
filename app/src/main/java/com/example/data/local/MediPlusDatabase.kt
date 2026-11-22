package com.example.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.data.local.dao.MedicineDao
import com.example.data.local.entity.MedicineEntity

@Database(entities = [MedicineEntity::class], version = 1, exportSchema = false)
abstract class MediPlusDatabase : RoomDatabase() {
  abstract fun medicineDao(): MedicineDao

  companion object {
    @Volatile private var INSTANCE: MediPlusDatabase? = null

    fun getInstance(context: Context): MediPlusDatabase =
      INSTANCE
        ?: synchronized(this) {
          INSTANCE
            ?: Room.databaseBuilder(
                context.applicationContext,
                MediPlusDatabase::class.java,
                "mediplus_bd.db",
              )
              .build()
              .also { INSTANCE = it }
        }
  }
}
