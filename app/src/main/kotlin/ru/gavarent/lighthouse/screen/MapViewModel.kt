package ru.gavarent.lighthouse.screen

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.gavarent.lighthouse.db.AppDatabase
import ru.gavarent.lighthouse.db.Sea
import ru.gavarent.lighthouse.db.SeaMark

class MapViewModel(private val appDatabase: AppDatabase) : ViewModel() {

    private val _seaMarksFlow: MutableStateFlow<List<SeaMark>> = MutableStateFlow(listOf())
    val seaMarksFlow: StateFlow<List<SeaMark>> = _seaMarksFlow


    init {
        GlobalScope.launch(Dispatchers.IO) {
            _seaMarksFlow.value = appDatabase.seaMarkDao().getAll()
            val sea = Sea()
            sea.uid = appDatabase.seaDao().insert(sea)


        }
    }


}
