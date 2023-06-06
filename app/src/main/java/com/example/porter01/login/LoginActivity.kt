package com.example.porter01.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commit
import com.example.porter01.R
import com.example.porter01.login.signIn.SignInFragment

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        if (savedInstanceState == null) {
            supportFragmentManager.commit (true){
                setReorderingAllowed(true)
                add(R.id.fragment_container_login, SignInFragment())
            }
        }
    }
}

