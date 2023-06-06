package com.example.porter01.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.porter01.login.signIn.SignInViewModel
import com.example.porter01.login.signUp.SignUpViewModel
import com.example.porter01.repository.Repository

 class ViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory {
     override fun <T : ViewModel> create(modelClass: Class<T>): T {
         return if(modelClass.isAssignableFrom(SignInViewModel::class.java))
             SignInViewModel(repository) as T
         else if (modelClass.isAssignableFrom(SignUpViewModel::class.java))
             SignUpViewModel(repository) as T
         else throw IllegalArgumentException("Unknown ViewModel class")
     }
}