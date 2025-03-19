package com.example.expensemanagercompose.ui.screens.login

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {
    private val _userName = MutableStateFlow("")
    val userName: StateFlow<String> = _userName

    private val _userEmail = MutableStateFlow("")
    val userEmail: StateFlow<String> = _userEmail

    private val _userPassword = MutableStateFlow("")
    val userPassword: StateFlow<String> = _userPassword

    private val _isLogin = MutableStateFlow(true)
    val isLogin: StateFlow<Boolean> = _isLogin

    fun onUserNameChange(newName: String) {
        _userName.value = newName
    }

    fun onUserEmailChange(newEmail: String) {
        _userEmail.value = newEmail
    }

    fun onUserPasswordChange(newPassword: String) {
        _userPassword.value = newPassword
    }

    fun onLoginChange() {
        _isLogin.value = !_isLogin.value
    }
}