package com.app.tours.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.tours.repositories.Users.RegisterRepository
import com.app.tours.services.dto.UsersDto


class RegisterViewModel : ViewModel(){
    var userLiveData = MutableLiveData<UsersDto>()
    var registerRepository = RegisterRepository()

    init {
        userLiveData = registerRepository.register
    }
    fun makeApiCallRegister(email : String, password: String, name: String, last_name: String, country: String, birth_date:String, type: String) {
        registerRepository.register(email, password,name,last_name,country,birth_date,type)
    }
}