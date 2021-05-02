package com.app.tours

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_register.*
import kotlinx.android.synthetic.main.fragment_register.view.*
import java.util.regex.Pattern


class RegisterFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_register, container, false)
        view.floatingBackButton.setOnClickListener { Navigation.findNavController(view).navigate(R.id.action_registerFragment_to_loginFragment)}
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        registerName.addTextChangedListener(registerTextWatcher);
        registerLastName.addTextChangedListener(registerTextWatcher);
        registerEmail.addTextChangedListener(registerTextWatcher);
        registerPassword.addTextChangedListener(registerTextWatcher);
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

            if(name.equals("")){
                registerName.setError("Please enter a name");
            }
            else{
                registerName.setError(null);
            }
            if(lastName.equals("")){
                registerLastName.setError("Please enter a last name");
            }
            else{
                registerLastName.setError(null);
            }
            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                registerEmail.setError("Please enter a valid email address");
            }
            else{
                registerEmail.setError(null);
            }
            if (!passwordREGEX.matcher(password).matches()) {
                registerPassword.setError("Password must contain at least one number, special character and uppercase and lowercase letter");
            }
            else{
                registerPassword.setError(null);
            }
            registerButton.setEnabled(registerName.error == null && registerLastName.error == null && registerPassword.error == null && registerEmail.error == null)
        }

        override fun afterTextChanged(s: Editable) {

        }
    }

}