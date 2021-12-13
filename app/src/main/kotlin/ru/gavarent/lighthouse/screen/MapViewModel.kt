package ru.gavarent.lighthouse.screen

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import ru.gavarent.lighthouse.db.SeaMark
import ru.gavarent.lighthouse.db.SeaMarkTable

class MapViewModel(private val seaMarkTable: SeaMarkTable) : ViewModel() {

    private val _seaMarksFlow: MutableStateFlow<List<SeaMark>> = MutableStateFlow(listOf())
    val seaMarksFlow: StateFlow<List<SeaMark>> = _seaMarksFlow


    init {
        _seaMarksFlow.value = seaMarkTable.data
    }



}
