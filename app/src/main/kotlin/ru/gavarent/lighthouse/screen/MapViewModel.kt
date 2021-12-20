package ru.gavarent.lighthouse.screen

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.gavarent.lighthouse.db.*

class MapViewModel(private val appDatabase: AppDatabase) : ViewModel() {

    private val _seaMarksFlow: MutableStateFlow<List<SeaMark>> = MutableStateFlow(listOf())
    val seaMarksFlow: StateFlow<List<SeaMark>> = _seaMarksFlow


    init {
        GlobalScope.launch(Dispatchers.IO) {
//            val sea = Sea()
//            sea.uid = appDatabase.seaDao().insert(sea)
//            val seaTranslation =
//                SeaTranslation(languageCode = "ru", seaOwnerId = sea.uid, title = "Балтийское море")
//            seaTranslation.uid = appDatabase.seaTranslationDao().insert(seaTranslation)
//            val countryRu = Country()
//            countryRu.uid = appDatabase.countryDao().insert(countryRu)
//            val countryTranslationRu = CountryTranslation(
//                countryOwnerId = countryRu.uid,
//                languageCode = "ru",
//                title = "Россия"
//            )
//            appDatabase.countryTranslationDao().insert(countryTranslationRu)
//
//
//            val countryLv = Country()
//            countryLv.uid = appDatabase.countryDao().insert(countryLv)
//            val countryTranslationLv = CountryTranslation(
//                countryOwnerId = countryLv.uid,
//                languageCode = "ru",
//                title = "Латвия"
//            )
//            appDatabase.countryTranslationDao().insert(countryTranslationRu)
//            val seaMark = SeaMark(
//                navigationType = SeaMarkType.HOUSE,
//                latitude = 60.0232,
//                longitude = 29.3231,
//                countryKey = countryRu.uid,
//                seaKey = sea.uid
//            )
//            val seaMark2 = SeaMark(
//                navigationType = SeaMarkType.HOUSE,
//                latitude = 56.382389,
//                longitude = 20.981642,
//                countryKey = countryLv.uid,
//                seaKey = sea.uid
//            )
//
//            val insert1 = appDatabase.seaMarkDao().insert(seaMark)
//            val insert2 = appDatabase.seaMarkDao().insert(seaMark2)
//
//
//            val seaMarkTranslation1 = SeaMarkTranslation(
//                seaMarkOwnerId = insert1,
//                languageCode = "ru",
//                title = ""
//            )
//            val seaMarkTranslation2 = SeaMarkTranslation(
//                seaMarkOwnerId = insert1,
//                languageCode = "ru",
//                title = ""
//            )
//            appDatabase.seaMarkTranslationDao().insert(seaMarkTranslation1)
//            appDatabase.seaMarkTranslationDao().insert(seaMarkTranslation2)

            _seaMarksFlow.value = appDatabase.seaMarkDao().getAll()

//            appDatabase.close()


        }
    }


}
