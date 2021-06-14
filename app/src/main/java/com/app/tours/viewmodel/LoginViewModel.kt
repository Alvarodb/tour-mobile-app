package com.app.tours.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.tours.repositories.Users.LoginRepository
import com.app.tours.services.dto.UsersDto

class LoginViewModel : ViewModel() {
    var userLiveData = MutableLiveData<UsersDto>()
    var loginRepository = LoginRepository()

    init {
        userLiveData = loginRepository.login
    }
    fun makeApiCallLogin(email: String, password: String) {
        loginRepository.login(email, password)
    }
}