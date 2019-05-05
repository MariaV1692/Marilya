package com.mariav.marilya.Controller

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View

import android.content.Intent
import com.mariav.marilya.R

/**
 * A login screen that offers login via email/password.
 */
class LoginActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    fun onBtnSignInClicked(view: View) {

    }

    fun onBtnSignUpClicked(view: View) {
        val signup_screen = Intent(this, CreateUserActivity::class.java)
        startActivity(signup_screen)
    }
}
