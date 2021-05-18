package com.app.tours.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.app.tours.R
import kotlinx.android.synthetic.main.fragment_login.view.*
import kotlinx.android.synthetic.main.fragment_logout.view.*

class LogoutFragment : Fragment() {

    private lateinit var preferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        preferences = requireActivity().getSharedPreferences("data", Context.MODE_PRIVATE)
        val view = inflater.inflate(R.layout.fragment_logout, container, false)
        view.btLogout.setOnClickListener {
            preferences.edit().remove("user_logged").apply()
            Navigation.findNavController(view).navigate(R.id.action_logoutFragment_to_loginFragment)
        }
        return view
    }
}