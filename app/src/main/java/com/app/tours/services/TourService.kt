package com.app.tours.services

import com.app.tours.services.dto.TourDto
import retrofit2.Call
import retrofit2.http.GET

interface TourService {
    @GET("/tours")
    fun getTours(): Call<List<TourDto>>
}