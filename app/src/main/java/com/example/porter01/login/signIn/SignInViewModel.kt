package com.example.porter01.login.signIn

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.porter01.repository.Repository

class SignInViewModel(private val repository : Repository) : ViewModel() {
    private var _signInResult = MutableLiveData<Boolean>()
    val signInResult :LiveData<Boolean> = _signInResult
    fun loginUser(userName : String ,password : String):Boolean{
        val user = repository.getUser(userName,password)
        _signInResult.value = user != null
        return true
    }
}
