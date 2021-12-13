package ru.gavarent.lighthouse.db

import ru.gavarent.lighthouse.R

data class SeaMark(
    val title: Int,
    val navigationType: SeaMarkType,
    val latitude: Double,
    val longitude: Double,
    val country: String,
    val sea: Sea
)


class SeaMarkTable {
    val data = listOf(
        SeaMark(
            title = R.string.sea_mark_tolbuhin,
            navigationType = SeaMarkType.HOUSE,
            latitude = 60.0232,
            longitude = 29.3231,
            country = "ru",
            sea = Sea.BALTIC
        ),
        SeaMark(
            title = R.string.sea_mark_bernati,
            navigationType = SeaMarkType.HOUSE,
            latitude = 56.382389,
            longitude = 20.981642,
            country = "lv",
            sea = Sea.BALTIC
        )
    )


    //60°02′32″ с. ш. 029°32′31″ в. д.HЯ,
}

enum class SeaMarkType {

    VESSEL,
    HOUSE,
    LANDMARK
}

enum class Sea {
    BALTIC
}