package com.app.tours.viewmodels



import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.tours.repositories.ToursRepository
import com.app.tours.services.dto.TourDto
import java.sql.Date
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class TourViewModel : ViewModel() {
    var toursLiveData = MutableLiveData<List<TourDto>>()
    var toursRepository = ToursRepository()

    init {
        toursLiveData = toursRepository.tours
    }

    fun getTours() {
        toursRepository.getTours()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getFilterTours(word:String, date1:String, date2:String): List<TourDto>? {

        val filterByName= toursLiveData.value?.filter{
            it.name.contains(word)
        }

        if(date1!==""&&date2!==""){
            val pdate1=LocalDate.of(date1.substring(6,10).toString().toInt(),date1.substring(3,5).toString().toInt(),date1.substring(0,2).toString().toInt())
            val pdate2=LocalDate.of(date2.substring(6,10).toString().toInt(),date2.substring(3,5).toString().toInt(),date2.subSequence(0,2).toString().toInt())
            if (filterByName != null) {
                return filterByName.filter {
                    val objectDate=LocalDate.parse(it.start_date.subSequence(0,10))
                        objectDate.isAfter(pdate1)&&objectDate.isBefore(pdate2)
                }
            }
        }
        return filterByName;
    }
}