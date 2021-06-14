package com.app.tours.services

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceBuilder {

    var BASE_URL = "http://bd56dd7efa2b.ngrok.io/"

    val retrofit : Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

    fun<T> buildService(service: Class<T>): T{
        return retrofit.create(service)
    }
}
