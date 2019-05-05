package com.mariav.marilya.Controller

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.mariav.marilya.R
import com.mariav.marilya.Services.AuthService
import kotlinx.android.synthetic.main.activity_create_user.*

class CreateUserActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_user)
    }

    fun onCreateUserClicked(view: View) {
        AuthService.registerUser(this,inpRegEmail.text.toString(),inpRegPass.text.toString()){complete ->
            if(complete){

            }
        }
    }
}
