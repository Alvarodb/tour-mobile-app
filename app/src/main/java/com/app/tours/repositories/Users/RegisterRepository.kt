package com.app.tours.repositories.Users

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.app.tours.Constants
import com.app.tours.services.UsersService
import com.app.tours.services.dto.RegisterDto
import com.app.tours.services.dto.UsersDto
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RegisterRepository {
    var register = MutableLiveData<UsersDto>()

    init {
        register.value = UsersDto("","")
    }
    private fun getRetrofit(): UsersService {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constants.BASE_URL)
            .build()
            .create(UsersService::class.java)
    }

    fun register(email : String, password: String, name: String, last_name: String, country: String, birth_date:String, type: String) {
        getRetrofit().register(RegisterDto(email, password,name,last_name,country,birth_date,type)).enqueue(object : Callback<UsersDto> {
            override fun onFailure(call: Call<UsersDto>, t: Throwable){
                Log.d("TAG_", "An error happened!")
            }
            override fun onResponse(
                call: Call<UsersDto>,
                response: Response<UsersDto>
            ){
                Log.d("TAG_", response.body().toString())
                register.value = response.body()
            }
        })
    }
}