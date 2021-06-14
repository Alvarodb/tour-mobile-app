package com.app.tours.fragments

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.app.tours.R
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_login.view.*
import android.content.SharedPreferences
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.app.tours.services.dto.UsersDto
import com.app.tours.viewmodel.LoginViewModel
import java.util.regex.Pattern


class LoginFragment : Fragment() {

    private lateinit var preferences: SharedPreferences
    private val model: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        view.register.setOnClickListener { Navigation.findNavController(view).navigate(
            R.id.action_loginFragment_to_registerFragment
        )}
        preferences = requireActivity().getSharedPreferences("data",Context.MODE_PRIVATE)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        email.addTextChangedListener(loginTextWatcher);
        password.addTextChangedListener(loginTextWatcher);

        if(preferences.getString("user_logged",null) != null){
            Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_logoutFragment)
        }
        view.login.setOnClickListener {
            model.makeApiCallLogin(view.email.text.toString(), view.password.text.toString())
        }
        model.userLiveData.observe(viewLifecycleOwner, Observer {
            if (it != null && it.email.isNotEmpty() && it.password.isNotEmpty()) {
                Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_tourSearch)
                preferences.edit().putString("user_logged",view.email.text.toString()).apply()
            } else if (it == null){
                val builder = AlertDialog.Builder(requireActivity())
                builder.setTitle("User is not registered") //add this to string
                builder.setMessage("User not found") // add this to string
                builder.setPositiveButton("OK") { dialog, which ->
                }
                val dialog: AlertDialog = builder.create()
                dialog.show()
            }
        })

    }

    private val loginTextWatcher: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            val emailInput: String = email.getText().toString().trim()
            val passwordInput: String = password.getText().toString().trim()
            val passwordREGEX = Pattern.compile("^" +
                    "(?=.*[0-9])" +         //at least 1 digit
                    "(?=.*[a-z])" +         //at least 1 lower case letter
                    "(?=.*[A-Z])" +         //at least 1 upper case letter
                    "(?=.*[a-zA-Z])" +      //any letter
                    "(?=.*[@#$%^&+=])" +    //at least 1 special character
                    "(?=\\S+$)" +           //no white spaces
                    ".{8,}" +               //at least 8 characters
                    "$");

            if (passwordInput.equals("")){
                password.setError(getResources().getString(R.string.password_empty_error));
            }
            else{
                password.setError(null);
            }
            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
                email.setError(getResources().getString(R.string.email_valid_error));
            }
            else{
                email.setError(null);
            }
            if (!passwordREGEX.matcher(passwordInput).matches()) {
                password.setError(getResources().getString(R.string.password_valid_error));
            }
            else{
                password.setError(null);
            }
            login.setEnabled(email.error == null && password.error == null)
        }


        override fun afterTextChanged(s: Editable) {

        }
    }
}