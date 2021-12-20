package ru.gavarent.lighthouse.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [
        SeaMark::class, SeaMarkTranslation::class,
        Sea::class, SeaTranslation::class,
        Country::class, CountryTranslation::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun seaMarkDao(): SeaMarkDao
    abstract fun seaDao(): SeaDao
    abstract fun seaTranslationDao(): SeaTranslationDao

}