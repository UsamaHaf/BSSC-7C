package com.techwarsol.testapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import com.techwarsol.testapp.databinding.ActivityHomePageBinding
import com.techwarsol.testapp.databinding.ActivityRegistrationBinding

class HomePageActivity : AppCompatActivity() {

    lateinit var binding: ActivityHomePageBinding

    lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)

        binding = ActivityHomePageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        binding.btnLogout.setOnClickListener {

            auth.signOut()

            startActivity(Intent(this , LoginActivity::class.java))

        }




    }
}