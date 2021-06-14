package com.app.tours.repositories



import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.app.tours.Constants
import com.app.tours.services.TourService
import com.app.tours.services.dto.TourDto
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class ToursRepository {
    var tours = MutableLiveData<List<TourDto>>()

    init {
        tours.value = mutableListOf()
    }

    private fun getRetrofit(): TourService {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(TourService::class.java)
    }

    fun getTours() {
        getRetrofit().getTours().enqueue(object : Callback<List<TourDto>> {

            /* The HTTP call failed. This method is run on the main thread */
            override fun onFailure(call: Call<List<TourDto>>, t: Throwable) {
                Log.d("TAG_", "An error happened!")

            }

            /* The HTTP call was successful, we should still check status code and response body
             * on a production app. This method is run on the main thread */
            override fun onResponse(
                call: Call<List<TourDto>>,
                response: Response<List<TourDto>>
            ) {
                /* This will print the response of the network call to the Logcat */
                Log.d("TAG_", response.body().toString())
                tours.value = response.body().orEmpty()
            }
        })
    }
}