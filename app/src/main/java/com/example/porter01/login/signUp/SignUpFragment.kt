package com.example.porter01.login.signUp

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import com.example.porter01.factory.ViewModelFactory
import com.example.porter01.databinding.FragmentSignUpBinding
import com.example.porter01.helpers.SharedPreferencePrivateModeHelper
import com.example.porter01.mainUi.MainActivity
import com.example.porter01.helpers.database.DatabaseHelper
import com.example.porter01.repository.Repository
import com.example.porter01.utility.TextValidation
private const val SHARED_PREFERENCES_NAME = "loginCredentials"
private const val SHARED_PREFERENCES_KEY = "id"

class SignUpFragment : Fragment() {
    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel : SignUpViewModel
    private lateinit var databaseHelper : DatabaseHelper
    private lateinit var repository : Repository
    private lateinit var firstName : EditText
    private lateinit var lastName : EditText
    private lateinit var userName : EditText
    private lateinit var password : EditText
    private lateinit var email : EditText
    private lateinit var phoneNo : EditText
    private lateinit var sharedPreferences : SharedPreferencePrivateModeHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        sharedPreferences = SharedPreferencePrivateModeHelper(requireActivity(), SHARED_PREFERENCES_NAME)
        _binding = FragmentSignUpBinding.inflate(inflater,container,false)
        databaseHelper = DatabaseHelper(requireContext())
        repository = Repository(databaseHelper)
        firstName = binding.editTextFirstName
        lastName = binding.editTextLastName
        userName = binding.editTextUserName
        password = binding.editTextPassword
        email = binding.editTextEmail
        phoneNo = binding.editTextPhoneNo
        viewModel = ViewModelProvider(requireActivity(), ViewModelFactory(repository))[SignUpViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonRegister.setOnClickListener {
            if(checkAllFields()){
                viewModel.registerUser(firstName.text.toString(),lastName.text.toString(),userName.text.toString(),password.text.toString(),email.text.toString(),phoneNo.text.toString())
            }
        }
        viewModel.registerResult.observe(requireActivity()){ result ->
            if(result){
                val userId = repository.getUser(userName.text.toString().trim(),password.text.toString().trim())?.id
                sharedPreferences.setData(SHARED_PREFERENCES_KEY,userId.toString())
                Intent(activity, MainActivity::class.java).also { startActivity(it) }
                requireActivity().finish()
            }
        }
    }

    private fun checkAllFields(): Boolean {
        var flag = true
        if (!TextValidation.validateFirstName(firstName.text.toString().trim())) {
            firstName.error = "Enter valid first name (Min 3 Alphabet)"
            flag = false
        }
        if (!TextValidation.validateLastName(lastName.text.toString().trim())) {
            lastName.error = "Enter valid last name (Only Alphabet is allowed)"
            flag = false
        }
        if (!TextValidation.validateUserName(userName.text.toString().trim())) {
            userName.error = "Enter valid user name (Should contain min 5 characters/digit/lower case/upper case)"
            flag = false
        }
        if (!TextValidation.validatePassword(password.text.toString().trim())) {
            password.error = "Enter valid password (Min 8 characters/ digit/Lower case/upper case/special character)"
            flag = false
        }
        if (!TextValidation.validateEmail(email.text.toString().trim())) {
            email.error = "Enter valid email"
            flag = false
        }
        if (!TextValidation.validatePhoneNo(phoneNo.text.toString().trim())) {
            phoneNo.error = "Enter valid Phone number"
            flag = false
        }
        return flag
    }
}