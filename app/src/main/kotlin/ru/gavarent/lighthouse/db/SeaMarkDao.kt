package ru.gavarent.lighthouse.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface SeaMarkDao {
    @Query("SELECT * FROM seaMark")
    suspend fun getAll(): List<SeaMark>

}

@Dao
interface SeaDao {
    @Query("SELECT * FROM sea")
    suspend fun getAll(): List<Sea>

    @Insert
    suspend fun insert(sea: Sea) : Long
}

@Dao
interface SeaTranslationDao {
    @Insert
    suspend fun insert(seaTranslation: SeaTranslation): Long
}
