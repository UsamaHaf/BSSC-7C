package com.techwarsol.testapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.techwarsol.testapp.Model.Product
import com.techwarsol.testapp.databinding.ActivityHomePageBinding
import com.techwarsol.testapp.databinding.ActivityRegistrationBinding

class HomePageActivity : AppCompatActivity() {

    lateinit var binding: ActivityHomePageBinding

    lateinit var auth: FirebaseAuth
    lateinit var dbRef: DatabaseReference
    lateinit var database: FirebaseDatabase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)

        binding = ActivityHomePageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()

        binding.btnLogout.setOnClickListener {
            auth.signOut()
            startActivity(Intent(this, LoginActivity::class.java))
        }

        binding.btnAddProduct.setOnClickListener {

            val name = binding.etProductName.text.toString().trim()
            val desc = binding.etProductDescription.text.toString().trim()
            val category = binding.etProductCategory.text.toString().trim()
            val priceStr = binding.etProductPrice.text.toString().trim()
            val stockStr = binding.etProductStock.text.toString().trim()

            val price = priceStr.toDoubleOrNull()
            val stock = stockStr.toIntOrNull()


            if (name.isEmpty() || desc.isEmpty() || category.isEmpty()) {
                Toast.makeText(this, "Enter Name, Desc, Category of Product", Toast.LENGTH_SHORT)
                    .show()

            } else if (price == null || stock == null) {
                Toast.makeText(this, "Enter Price and Stock of Product", Toast.LENGTH_SHORT).show()

            } else {
                addProduct(name, desc, category, price, stock)
            }

        }


    }

    private fun addProduct(
        name: String,
        desc: String,
        category: String,
        price: Double,
        stock: Int
    ) {

        binding.pbAddProduct.visibility = View.VISIBLE
        binding.btnAddProduct.isClickable = false
        binding.btnAddProduct.isEnabled = false

        dbRef = database.getReference("Product")

        val productID = dbRef.push().key
        if (productID != null) {
            val product = Product(name, desc, category, price, stock)

            dbRef.child(productID).setValue(product)
                .addOnSuccessListener {
                    Toast.makeText(this, "Product Added Successfully", Toast.LENGTH_SHORT)
                        .show()



                    binding.etProductPrice.setText("")
                    binding.etProductDescription.setText("")
                    binding.etProductStock.setText("")
                    binding.etProductName.setText("")
                    binding.etProductCategory.setText("")

                    binding.pbAddProduct.visibility = View.GONE
                    binding.btnAddProduct.isClickable = true
                    binding.btnAddProduct.isEnabled = true
                }
                .addOnFailureListener {

                    binding.pbAddProduct.visibility = View.GONE
                    binding.btnAddProduct.isClickable = true
                    binding.btnAddProduct.isEnabled = true

                    Toast.makeText(this, "Adding Product Failed", Toast.LENGTH_SHORT)
                        .show()
                }
        }


    }
}