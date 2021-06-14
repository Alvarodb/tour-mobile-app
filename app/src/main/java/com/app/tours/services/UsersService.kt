package com.app.tours.services

import androidx.lifecycle.MutableLiveData
import com.app.tours.services.dto.UsersDto
import com.app.tours.services.dto.RegisterDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST



interface UsersService {

    @POST("user/")
    fun login(@Body user: UsersDto): Call<UsersDto>

    @POST("register/")
    fun register(@Body user: RegisterDto): Call<UsersDto>

}