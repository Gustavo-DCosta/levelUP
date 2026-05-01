package com.example.levelup

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class NetworkViewModel(application: Application) : AndroidViewModel(application) {
    private val checker = NetworkChecker(application)

    private val _isConnected = MutableStateFlow(false)
    val isConnected: StateFlow<Boolean> = _isConnected

    init {
        android.util.Log.d("NetworkViewModel", "ViewModel created, checking now...")
        checkConnection()
    }

    fun checkConnection() {
        android.util.Log.d("NetworkViewModel", "checkConnection called")
        _isConnected.value = checker.isOnLevelUpNetwork()
    }
}