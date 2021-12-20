package ru.gavarent.lighthouse.db

import androidx.room.*


@Entity
data class SeaMark(
    @PrimaryKey(autoGenerate = true) val uid: Long = 0,
    val title: Int,
    val navigationType: SeaMarkType = SeaMarkType.UNDEFINED,
    val latitude: Double,
    val longitude: Double,
    val country: String,
    val seaKey: Long
)

@Entity
data class SeaMarkTranslation(
    @PrimaryKey(autoGenerate = true) val uid: Long = 0,
    val seaMarkOwnerId: Long,
    @ColumnInfo(name = "title") val title: String

)

data class SeaMarkAndTranslation(
    @Embedded val seaMark: SeaMark,
    @Relation(
        parentColumn = "uid",
        entityColumn = "seaMarkOwnerId"
    )
    val seaMarkTranslation: SeaMarkTranslation
)

@Entity
data class Sea(
    @PrimaryKey(autoGenerate = true)
    var uid: Long = 0,
)

@Entity
data class SeaTranslation(
    @PrimaryKey(autoGenerate = true) val uid: Long = 0,
    val seaOwnerId: Long,
    @ColumnInfo(name = "name") val title: String
)

data class SeaAndTranslation(
    @Embedded val sea: Sea,
    @Relation(
        parentColumn = "uid",
        entityColumn = "seaOwnerId"
    )
    val seaTranslation: SeaTranslation

)

@Entity
data class Country(
    @PrimaryKey(autoGenerate = true) val uid: Long = 0,
)

@Entity
data class CountryTranslation(
    @PrimaryKey(autoGenerate = true) val uid: Long = 0,
    val countryOwnerId: Long,
    @ColumnInfo(name = "name") val title: String
)

enum class SeaMarkType {
    UNDEFINED,
    VESSEL,
    HOUSE,
    LANDMARK
}

