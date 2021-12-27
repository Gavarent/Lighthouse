package ru.gavarent.lighthouse.screen

import androidx.compose.ui.text.intl.Locale
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.gavarent.lighthouse.db.AppDatabase
import ru.gavarent.lighthouse.screen.map.MapMarker

class MapViewModel(private val appDatabase: AppDatabase) : ViewModel() {

    private val _seaMarksFlow: MutableStateFlow<List<MapMarker>> = MutableStateFlow(listOf())
    val seaMarksFlow: StateFlow<List<MapMarker>> = _seaMarksFlow

    init {
        initMarkers(LatLng(60.0232, 29.3231))
    }

    fun initMarkers(latLng: LatLng) {

        viewModelScope.launch(Dispatchers.IO) {

            val languageTag = Locale.current.toLanguageTag()

            val allByLocale =
                appDatabase.seaMarkDao().getAllByLocale(listOf(languageTag, "en"))

            val markers = allByLocale
                .filter {
                    it.value.isNotEmpty()
                }
                .mapValues {

                    when (it.value.size) {
                        1 -> {
                            return@mapValues it.value.first()
                        }
                        else -> {
                            for (seaMarkTranslation in it.value) {
                                if (seaMarkTranslation.languageCode == languageTag) {
                                    return@mapValues seaMarkTranslation
                                }
                            }
                            for (seaMarkTranslation in it.value) {
                                if (seaMarkTranslation.languageCode == "en") {
                                    return@mapValues seaMarkTranslation
                                }
                            }
                            it.value.first()
                        }
                    }
                }
                .mapNotNull {
                    val markerOptions = MarkerOptions()
                    markerOptions
                   //     .icon(BitmapDescriptorFactory.fromResource(it.key.navigationType.mapIcon))
                        .title(it.value.title)
                        .position(LatLng(it.key.latitude, it.key.longitude))
                        .snippet("SNIPPETS")
                    return@mapNotNull MapMarker(markerOptions, it.key.uid)

                }

            _seaMarksFlow.value = markers
        }
    }
}
