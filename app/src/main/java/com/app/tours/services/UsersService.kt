package com.app.tours.services

import com.app.tours.services.dto.UsersDto
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST



interface UsersService {

    @POST("user/")
    fun login(@Body user: UsersDto): Call<UsersDto>

}