package com.app.tours.fragments

import android.app.DatePickerDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.navigation.Navigation
import com.app.tours.R
import com.app.tours.services.ServiceBuilder
import com.app.tours.services.UsersService
import com.app.tours.services.dto.RegisterDto
import com.app.tours.services.dto.UsersDto
import kotlinx.android.synthetic.main.fragment_login.view.*
import kotlinx.android.synthetic.main.fragment_register.*
import kotlinx.android.synthetic.main.fragment_register.view.*
import kotlinx.android.synthetic.main.fragment_tour_search.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.regex.Pattern


class RegisterFragment : Fragment() {


    private fun twoDigits(n: Int): String? {
        return if (n <= 9) "0$n" else n.toString()
    }

    private fun showDatePickerDialog(editText: EditText) {
        val newFragment =
            DatePickerFragment.newInstance(DatePickerDialog.OnDateSetListener { datePicker, year, month, day ->
                val selectedDate: String =
                    twoDigits(day).toString() + "/" + twoDigits(month + 1) + "/" + year
                editText.setText(selectedDate)
            })
        newFragment.show(requireActivity().supportFragmentManager, "datePicker")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_register, container, false)
        view.floatingBackButton.setOnClickListener { Navigation.findNavController(view).navigate(
            R.id.action_registerFragment_to_loginFragment
        )}
        view.registerButton.setOnClickListener {
            register(view)
        }
        return view
    }

    fun register(view: View) {
        val apiInterface = ServiceBuilder.buildService(UsersService::class.java)

        apiInterface.register(RegisterDto(view.registerEmail.text.toString(), view.registerPassword.text.toString(),view.registerName.text.toString()
        , view.registerLastName.text.toString(), view.registerCountry.text.toString(), view.registerBirthday.text.toString() ,"user"
        )).enqueue(object :
            Callback<UsersDto> {
            override fun onResponse(call: Call<UsersDto>?, response: Response<UsersDto>?) {
                if (response?.body() == null) {
                    val builder = AlertDialog.Builder(activity!!)
                    builder.setTitle("User Created") //add this to string
                    builder.setMessage("Click to continue") // add this to string
                    builder.setPositiveButton("OK") { dialog, which ->
                    }
                    val dialog: AlertDialog = builder.create()
                    dialog.show()
                } else {
                    val builder = AlertDialog.Builder(activity!!)
                    builder.setTitle("User alredy exits") //add this to string
                    builder.setMessage("Email being used") // add this to string
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
        registerBirthday.setOnClickListener {
            showDatePickerDialog(registerBirthday)
        }

        super.onViewCreated(view, savedInstanceState)
        registerName.addTextChangedListener(registerTextWatcher);
        registerLastName.addTextChangedListener(registerTextWatcher);
        registerEmail.addTextChangedListener(registerTextWatcher);
        registerPassword.addTextChangedListener(registerTextWatcher);
        registerCountry.addTextChangedListener(registerTextWatcher);
        registerBirthday.addTextChangedListener(registerTextWatcher);



    }

    private val registerTextWatcher: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            val passwordREGEX = Pattern.compile("^" +
                    "(?=.*[0-9])" +         //at least 1 digit
                    "(?=.*[a-z])" +         //at least 1 lower case letter
                    "(?=.*[A-Z])" +         //at least 1 upper case letter
                    "(?=.*[a-zA-Z])" +      //any letter
                    "(?=.*[@#$%^&+=])" +    //at least 1 special character
                    "(?=\\S+$)" +           //no white spaces
                    ".{8,}" +               //at least 8 characters
                    "$");
            val name: String = registerName.getText().toString().trim()
            val lastName: String = registerLastName.getText().toString().trim()
            val email: String = registerEmail.getText().toString().trim()
            val password: String = registerPassword.getText().toString().trim()
            val country:String=registerCountry.getText().toString().trim()
            val birthday:String=registerBirthday.getText().toString().trim()


            if(country.equals("")){
                registerCountry.setError(getResources().getString(R.string.country_empty_error));
            }
            else{
                registerCountry.setError(null);
            }
            if(birthday.equals("")){
                registerBirthday.setError(getResources().getString(R.string.birthday_empty_error));
            }
            else{
                registerBirthday.setError(null);
            }
            if(name.equals("")){
                registerName.setError(getResources().getString(R.string.name_empty_error));
            }
            else{
                registerName.setError(null);
            }
            if(lastName.equals("")){
                registerLastName.setError(getResources().getString(R.string.lastname_empty_error));
            }
            else{
                registerLastName.setError(null);
            }
            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                registerEmail.setError(getResources().getString(R.string.email_valid_error));
            }
            else{
                registerEmail.setError(null);
            }
            if (!passwordREGEX.matcher(password).matches()) {
                registerPassword.setError(getResources().getString(R.string.password_valid_error));
            }
            else{
                registerPassword.setError(null);
            }
            registerButton.setEnabled(registerName.error == null && registerLastName.error == null && registerPassword.error == null && registerEmail.error == null && registerCountry.error==null && registerBirthday.error==null)
        }

        override fun afterTextChanged(s: Editable) {

        }
    }

}