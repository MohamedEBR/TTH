package com.tasktrackinghelp.tth

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.time.LocalDate

class MainViewModel : ViewModel(){
    private val _events = MutableStateFlow<List<Event>>(emptyList())
    val events = _events.asStateFlow()

    fun addEvent(event: Event) {
        _events.value = _events.value + event
        logEvents()
    }

    var selectedDate by mutableStateOf(LocalDate.now())
        private set

    fun onDateSelected(date: LocalDate) {
        selectedDate = date
        onAddClick()
    }

    var isDialogShown by mutableStateOf(false)


    fun onAddClick(){
        isDialogShown = true
    }

    fun onDismissClick(){
        isDialogShown = false
    }

    fun logEvents() {
        _events.value.forEach { event ->
            Log.d("MainViewModel", "Event: $event")
        }
    }
}