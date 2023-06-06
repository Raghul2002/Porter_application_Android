package com.example.porter01.login.signUp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.porter01.model.User
import com.example.porter01.repository.Repository

class SignUpViewModel(private val repository: Repository) :ViewModel(){
    private var _registerResult : MutableLiveData<Boolean> = MutableLiveData<Boolean>()
    var registerResult :LiveData<Boolean> =_registerResult

    fun registerUser(firstName:String,lastName:String,userName : String,password:String,email:String,phoneNo : String){
        val user  = User(firstName,lastName,userName,password,email,phoneNo)
       repository.insertUser(user)
        _registerResult.value = true
    }
}