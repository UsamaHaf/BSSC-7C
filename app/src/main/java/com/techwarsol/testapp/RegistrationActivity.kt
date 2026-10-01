package com.techwarsol.testapp

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.techwarsol.testapp.databinding.ActivityRegistrationBinding
import java.util.Calendar

class RegistrationActivity : AppCompatActivity() {

    lateinit var binding: ActivityRegistrationBinding

    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        setDatePicker()
        setUpProvince()


        binding.btnSignUp.setOnClickListener {

            val txtUserName = binding.etUserName.text.toString()
            val txtEmail = binding.etEmail.text.toString()
            val txtPassword = binding.etPassword.text.toString()
            val txtPhone = binding.etPhone.text.toString()
            val txtDOB = binding.etDOB.text.toString()


            if (txtUserName.isEmpty()) {

                Toast.makeText(this, "Enter you UserName", Toast.LENGTH_SHORT)
                    .show()

            } else if (txtEmail.isEmpty() || txtPassword.isEmpty() || txtPhone.isEmpty()) {

                Toast.makeText(this@RegistrationActivity, "Enter All Field", Toast.LENGTH_SHORT)
                    .show()

            } else {

                binding.pbLoader.visibility = View.VISIBLE
                binding.btnSignUp.visibility = View.GONE

                auth.createUserWithEmailAndPassword(txtEmail , txtPassword)
                    .addOnCompleteListener { result ->
                        if(result.isSuccessful){

                            binding.pbLoader.visibility = View.GONE
                            binding.btnSignUp.visibility = View.VISIBLE

                            val intent = Intent(this@RegistrationActivity , LoginActivity::class.java)
                            intent.putExtra("welcomeTxt" , "Welcome Mr ${txtUserName}")
                            startActivity(intent)
                            Toast.makeText(this@RegistrationActivity, "Registration Success", Toast.LENGTH_SHORT).show()

                        }else{

                            binding.pbLoader.visibility = View.GONE
                            binding.btnSignUp.visibility = View.VISIBLE

                            Toast.makeText(this@RegistrationActivity, "Registration Failed", Toast.LENGTH_SHORT).show()

                        }
                    }





            }
        }
    }

    private fun setUpProvince() {
        val province = arrayOf("Select Province", "Punjab", "NWFP", "KPK", "Federal")

        val adapter = ArrayAdapter(
            this,
            androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
            province)

        adapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item)
        binding.provinceList.adapter = adapter
    }

    private fun setDatePicker() {

        binding.etDOB.setOnClickListener {

            val calender = Calendar.getInstance()

            val year = calender.get(Calendar.YEAR)
            val month = calender.get(Calendar.MONTH)
            val date = calender.get(Calendar.DATE)

            val datePicker = DatePickerDialog(
                this, { _, selectedyear, selectedmonth, selectedday ->

                    val date = "$selectedday, $selectedmonth , $selectedyear"

                    binding.etDOB.setText(date)
                }, year, month, date
            )

            datePicker.datePicker.maxDate = System.currentTimeMillis()
            datePicker.show()
        }

    }
}