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
    @GET("users/"   )
    fun getUsers(): Call<List<UsersDto>>


    companion object {

        var BASE_URL = "http://ff469c642570.ngrok.io/"

        fun create() : UsersService {

            val retrofit : Retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create<UsersService>(UsersService::class.java)

        }
    }
}