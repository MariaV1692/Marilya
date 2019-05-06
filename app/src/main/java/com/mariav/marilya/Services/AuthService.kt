package com.mariav.marilya.Services

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.mariav.marilya.Utilities.URL_LOGIN
import com.mariav.marilya.Utilities.URL_REGISTER
import org.json.JSONException
import org.json.JSONObject

object AuthService {
    var isLoggedIn = false
    var userEmail = ""
    var authToken = ""

    fun registerUser(context: Context, email: String,password: String, complete: (Boolean) -> Unit) {
        val jsonBody = JSONObject()
        jsonBody.put("email",email)
        jsonBody.put("password",password)
        val requestBody = jsonBody.toString()

        val registerRequest = object : StringRequest(Method.POST, URL_REGISTER,Response.Listener {  response ->
            println(response)
            complete(true)
        }, Response.ErrorListener {error ->
            val message = Toast.makeText(context,"Could not register user on url ${URL_REGISTER}: ${error.message}",Toast.LENGTH_LONG)
            message.show()
            Log.e("HTTP REQUEST","Could not register user on url ${URL_REGISTER}: $error")
            complete(false)
        }){
            override fun getBodyContentType(): String {
                return "application/json; charset=utf-8"
            }

            override fun getBody(): ByteArray {
                Log.d("HTTP REQUEST",requestBody)
                return requestBody.toByteArray()
            }
        }
        Volley.newRequestQueue(context).add(registerRequest)
    }

    fun loginUser(context: Context, email: String,password: String, complete: (Boolean) -> Unit) {
        val jsonBody = JSONObject()
        jsonBody.put("email",email)
        jsonBody.put("password",password)
        val requestBody = jsonBody.toString()
        val request = JSONObject()
        val loginRequest = object : JsonObjectRequest(Method.POST, URL_LOGIN,request,Response.Listener {response->
            try{
                authToken = response.getString("token")
                userEmail = response.getString("user")
                isLoggedIn = true
            }catch (error: JSONException){
                val message = Toast.makeText(context,"Could not login user: ${error.message}",Toast.LENGTH_LONG)
                message.show()
                Log.e("HTTP REQUEST","Could not login user: $error")
                complete(false)
            }


            complete(true)
        }, Response.ErrorListener {error ->

            complete(false)
        }){
            override fun getBodyContentType(): String {
                return "application/json; charset=utf-8"
            }

            override fun getBody(): ByteArray {
                Log.d("HTTP REQUEST",requestBody)
                return requestBody.toByteArray()
            }
        }
        Volley.newRequestQueue(context).add(loginRequest)
    }
}