package com.example.porter01.login.signIn


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.lifecycle.ViewModelProvider
import com.example.porter01.R
import com.example.porter01.factory.ViewModelFactory
import com.example.porter01.databinding.FragmentSignInBinding
import com.example.porter01.helpers.SharedPreferencePrivateModeHelper
import com.example.porter01.login.signUp.SignUpFragment
import com.example.porter01.mainUi.MainActivity
import com.example.porter01.helpers.database.DatabaseHelper
import com.example.porter01.repository.Repository
import com.example.porter01.utility.TextValidation

private const val SHARED_PREFERENCES_NAME = "loginCredentials"
private const val SHARED_PREFERENCES_KEY = "id"

class SignInFragment : Fragment() {
    private var _binding: FragmentSignInBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel : SignInViewModel
    private lateinit var databaseHelper : DatabaseHelper
    private lateinit var repository : Repository
    private lateinit var userName : EditText
    private lateinit var password : EditText
    private lateinit var sharedPreferences : SharedPreferencePrivateModeHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        sharedPreferences = SharedPreferencePrivateModeHelper(requireActivity(), SHARED_PREFERENCES_NAME)
        if(sharedPreferences.getData(SHARED_PREFERENCES_KEY)!=null){
            startMainActivity()
        }
        _binding = FragmentSignInBinding.inflate(inflater, container, false )
        databaseHelper = DatabaseHelper(requireContext())
        repository = Repository(databaseHelper)
        userName = binding.editTextUserName
        password = binding.editTextPassword
        requireContext()
        viewModel = ViewModelProvider(requireActivity(), ViewModelFactory(repository))[SignInViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonCreateAccount.setOnClickListener {
            parentFragmentManager.commit {
                setReorderingAllowed(true)
                replace(R.id.fragment_container_login, SignUpFragment())
                addToBackStack(null)
            }
        }
        binding.buttonLogin.setOnClickListener{
            if(checkAllFields()){
                viewModel.loginUser(userName.text.toString().trim(), password.text.toString().trim())
            }
        }
        viewModel.signInResult.observe(requireActivity()) {result ->
            if(result){
                val userId = repository.getUser(userName.text.toString().trim(),password.text.toString().trim())?.id
                sharedPreferences.setData(SHARED_PREFERENCES_KEY,userId.toString())
                startMainActivity()
            }
            else{
                Toast.makeText(requireContext(), "Invalid Login Credentials", Toast.LENGTH_SHORT).show()
            }
        }

    }
    private fun checkAllFields(): Boolean {
        var flag = true
        if (!TextValidation.validateUserName(userName.text.toString().trim())) {
            binding.editTextUserName.error = "Enter valid user name (Should contain min 5 characters/digit/lower case/upper case)"
            flag = false
        }
        if (!TextValidation.validatePassword(password.text.toString().trim())) {
            binding.editTextPassword.error = "Enter valid password (Min 8 characters/ digit/Lower case/upper case/special character)"
            flag = false
        }
        return flag
    }
    private fun startMainActivity(){
        Intent(activity, MainActivity::class.java).also { startActivity(it) }
        requireActivity().finish()
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}