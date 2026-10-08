package com.techwarsol.testapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    lateinit var etLoginEmail: EditText
    lateinit var welcomText: TextView
    lateinit var etLoginPassword: EditText
    lateinit var btnLogin: Button

    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = FirebaseAuth.getInstance()

        welcomText = findViewById<TextView>(R.id.welcomText)
        etLoginEmail = findViewById<EditText>(R.id.etLoginEmail)
        etLoginPassword = findViewById<EditText>(R.id.etLoginPassword)
        btnLogin = findViewById<Button>(R.id.btnLogin)



        val intent = intent.getStringExtra("welcomeTxt")
        welcomText.setText(intent)

        btnLogin.setOnClickListener {

            val email = etLoginEmail.text.toString()
            val password = etLoginPassword.text.toString()

            if(email.isEmpty() || password.isEmpty()){
                Toast.makeText(this, "Enter All Fields", Toast.LENGTH_LONG).show()
            }else {

                auth.signInWithEmailAndPassword(email , password)
                    .addOnCompleteListener {result ->

                        if(result.isSuccessful){

                            val intent = Intent(
                                this@LoginActivity,
                                HomePageActivity::class.java
                            )
                            startActivity(intent)

                            Toast.makeText(this, "Login Success", Toast.LENGTH_SHORT).show()

                        }else{
                            Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show()

                        }

                    }


            }

        }


    }
}