package com.example.levelup


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SportViewModel : ViewModel() {

    private val _status = MutableStateFlow<String?>(null)
    val status: StateFlow<String?> = _status

    fun sendSport(sport: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = ApiClient.sendSport(sport)
                _status.value = result
            } catch (e: Exception) {
                _status.value = "Error: ${e.message}"
            }
        }
    }
}