package com.app.tours.services.dto

data class TourDto(val id: String, val name: String, val description: String, val start_date: String, val price_for_person:String,  val duration: Int, val max_capacity:Int, val calification:Int, val Category: Int, val Location: Int);