package com.app.tours.fragments

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
import com.app.tours.services.ServiceBuilder
import com.app.tours.services.UsersService
import com.app.tours.services.dto.UsersDto
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_login.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        view.register.setOnClickListener { Navigation.findNavController(view).navigate(
            R.id.action_loginFragment_to_registerFragment
        )}
        view.login.setOnClickListener {
            login(view)
        }
        return view
    }
    fun login(view: View) {
        val apiInterface = ServiceBuilder.buildService(UsersService::class.java)

        apiInterface.login(UsersDto(view.email.text.toString(),view.password.text.toString())).enqueue(object :
            Callback<UsersDto> {
            override fun onResponse(call: Call<UsersDto>?, response: Response<UsersDto>?) {
                if (response?.body() != null) {
                    Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_tourSearch)
                } else {
                    val builder = AlertDialog.Builder(activity!!)
                    builder.setTitle("User is not registered") //add this to string
                    builder.setMessage("User not found") // add this to string
                    builder.setPositiveButton("OK") { dialog, which ->
                    }
                    val dialog: AlertDialog = builder.create()
                    dialog.show()
                }
            }
            override fun onFailure(call: Call<UsersDto>?, t: Throwable?) {
                t?.printStackTrace()
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        email.addTextChangedListener(loginTextWatcher);
        password.addTextChangedListener(loginTextWatcher);
    }

    private val loginTextWatcher: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            val emailInput: String = email.getText().toString().trim()
            val passwordInput: String = password.getText().toString().trim()

            if (passwordInput.equals("")){
                password.setError("Please enter a name");
            }
            else{
                password.setError(null);
            }
            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
                email.setError("Please enter a valid email address");
            }
            else{
                email.setError(null);
            }
            login.setEnabled(email.error == null && password.error == null)
        }


        override fun afterTextChanged(s: Editable) {

        }
    }
}