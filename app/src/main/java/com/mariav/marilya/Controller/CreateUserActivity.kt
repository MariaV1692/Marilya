package com.mariav.marilya.Controller

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.mariav.marilya.R
import com.mariav.marilya.Services.AuthService
import kotlinx.android.synthetic.main.activity_create_user.*

class CreateUserActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_user)
    }

    fun onCreateUserClicked(view: View) {
        val email = inpRegEmail.text.toString()
        val password = inpRegPass.text.toString()
        AuthService.registerUser(this,email,password){registerSuccess ->
            if(registerSuccess){
                AuthService.loginUser(this,email,password){loginSuccess ->
                    if(loginSuccess){
                        val message = Toast.makeText(this,"You are successfully logged in as ${AuthService.userEmail}", Toast.LENGTH_LONG)
                        message.show()
                    }
                }
            }
        }
    }
}
