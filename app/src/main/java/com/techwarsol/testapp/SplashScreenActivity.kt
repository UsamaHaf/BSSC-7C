package com.techwarsol.testapp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth

class SplashScreenActivity : AppCompatActivity() {

    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        auth = FirebaseAuth.getInstance()

        gotoNextScreen()

    }

    private fun gotoNextScreen() {
        Handler(Looper.getMainLooper()).postDelayed({

            val currentUser = auth.currentUser

            if(currentUser != null){
                val intent = Intent(this , HomePageActivity::class.java)
                startActivity(intent)
            }else{
                val intent = Intent(this , RegistrationActivity::class.java)
                startActivity(intent)
            }


        },2000)
    }
}