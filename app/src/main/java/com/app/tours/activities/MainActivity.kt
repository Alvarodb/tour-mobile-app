package com.app.tours.activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.app.tours.R
import com.app.tours.services.UsersService
import com.app.tours.services.dto.UsersDto
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val bottomNav: BottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        val navController = findNavController(R.id.fragment)
        bottomNav.setupWithNavController(navController)

        val apiInterface = UsersService.create()

        apiInterface.getUsers().enqueue( object : Callback<List<UsersDto>> {
            override fun onResponse(call: Call<List<UsersDto>>?, response: Response<List<UsersDto>>?){
                if(response?.body() != null) {
                    Log.i("TAG", Gson().toJson(response.body()))
                }
            }
            override fun onFailure(call: Call<List<UsersDto>>?, t: Throwable?) {
                t?.printStackTrace()
            }
        })
    }

}
