package com.techwarsol.testapp

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.techwarsol.testapp.Adapters.ProductsAdapter
import com.techwarsol.testapp.Model.Product
import com.techwarsol.testapp.databinding.ActivityShowProductsBinding

class ShowProductsActivity : AppCompatActivity() {


    lateinit var binding: ActivityShowProductsBinding
    lateinit var database: DatabaseReference
    lateinit var productsAdapter: ProductsAdapter
    private val productList = ArrayList<Product>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_products)

        binding = ActivityShowProductsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getFirebaseData()
        setUpRecyclerView()


    }

    private fun setUpRecyclerView() {
        productsAdapter = ProductsAdapter(productList)
        binding.rvProduct.adapter = productsAdapter
    }

    private fun getFirebaseData() {
        binding.pbShowProducts.visibility = View.VISIBLE

        database = FirebaseDatabase.getInstance().getReference("Product")

        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                productList.clear()

                for (childSnapshot in snapshot.children) {
                    val product = childSnapshot.getValue(Product::class.java)

                    if (product != null) {
                        productList.add(product)
                    }

                }
                productsAdapter.notifyDataSetChanged()
                binding.pbShowProducts.visibility = View.GONE


            }

            override fun onCancelled(error: DatabaseError) {
                binding.pbShowProducts.visibility = View.GONE
                Toast.makeText(
                    this@ShowProductsActivity,
                    "Failed To Load Data: $error",
                    Toast.LENGTH_SHORT
                ).show()

            }
        })
    }
}