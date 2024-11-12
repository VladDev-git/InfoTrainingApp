package com.example.infoapp

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.infoapp.db.MainDb
import com.example.infoapp.untils.ListItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val mainDb: MainDb
): ViewModel() {
    val mainList = mutableStateOf(emptyList<ListItem>())
    private val categoryState = MutableStateFlow("pull_ups")
    val currentCategory : StateFlow<String> = categoryState
    private var job: Job? = null

    fun getAllItemsByCategory(category: String) {
        job?.cancel()
        job = viewModelScope.launch {
            categoryState.value = category
            mainDb.dao.getAllItemsByCategory(category).collect { list ->
                mainList.value = list
            }
        }
    }

    fun getFavorites() {
        job?.cancel()
        job = viewModelScope.launch {
            mainDb.dao.getFavorites().collect { list ->
                mainList.value = list
            }
        }
    }

    fun insertItem(item: ListItem) = viewModelScope.launch {
        mainDb.dao.insertItem(item)
    }

    fun deleteItem(item: ListItem) = viewModelScope.launch {
        mainDb.dao.deleteItem(item)
    }
}