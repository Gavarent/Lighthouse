package ru.gavarent.lighthouse.db

import androidx.room.*


@Entity
data class SeaMark(
    @PrimaryKey val uid: Int,
    val title: Int,
    val navigationType: SeaMarkType = SeaMarkType.UNDEFINED,
    val latitude: Double,
    val longitude: Double,
    val country: String,
    val seaKey: Int
)

@Entity
data class SeaMarkTranslation(
    @PrimaryKey val uid: Int,
    val seaMarkOwnerId: Int,
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
    @PrimaryKey
    val uid: Int,
)

@Entity
data class SeaTranslation(
    @PrimaryKey val uid: Int,
    val seaOwnerId: Int,
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
    @PrimaryKey val uid: Int,
)

@Entity
data class CountryTranslation(
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "name") val title: String
)

enum class SeaMarkType {
    UNDEFINED,
    VESSEL,
    HOUSE,
    LANDMARK
}

