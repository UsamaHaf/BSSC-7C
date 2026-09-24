package com.techwarsol.testapp

import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class LoginActivity : AppCompatActivity() {

    lateinit var etLoginEmail: EditText
    lateinit var welcomText: TextView
    lateinit var etLoginPassword: EditText
    lateinit var btnLogin: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        welcomText = findViewById<TextView>(R.id.welcomText)
        etLoginEmail = findViewById<EditText>(R.id.etLoginEmail)
        etLoginPassword = findViewById<EditText>(R.id.etLoginPassword)
        btnLogin = findViewById<EditText>(R.id.btnLogin)

        val email = etLoginEmail.text.toString()
        val password = etLoginPassword.text.toString()

        val intent = intent.getStringExtra("welcomeTxt")
        welcomText.setText(intent)

        btnLogin.setOnClickListener {

            if(email.isEmpty() || password.isEmpty()){
                Toast.makeText(this, "Enter All Fields", Toast.LENGTH_LONG).show()
            }else {

                Toast.makeText(this, "Login Success", Toast.LENGTH_SHORT).show()

            }

        }


    }
}