package com.example.expensemanagercompose.ui.screens.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor() : ViewModel() {
    private val _navigateToHome = MutableStateFlow(false)
    val navigateToHome: StateFlow<Boolean> = _navigateToHome

    private val _navigateToLogin = MutableStateFlow(false)
    val navigateToLogin: StateFlow<Boolean> = _navigateToLogin

    init {
        //startSplash()
        startLogin()
    }

    private fun startSplash() {
        viewModelScope.launch {
            delay(3000)
            _navigateToHome.value = true
        }
    }

    private fun startLogin() {
        viewModelScope.launch {
            delay(3000)
            _navigateToLogin.value = true
        }
    }
}