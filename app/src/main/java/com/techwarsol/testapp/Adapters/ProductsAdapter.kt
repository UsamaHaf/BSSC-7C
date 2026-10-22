package com.techwarsol.testapp.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.techwarsol.testapp.Model.Product
import com.techwarsol.testapp.R
import com.techwarsol.testapp.databinding.ItemProductBinding

public class ProductsAdapter(private val productList: ArrayList<Product>) :
    RecyclerView.Adapter<ProductsAdapter.ProductViewHolder>() {

    inner class ProductViewHolder(val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductViewHolder {
        val binding = ItemProductBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ProductViewHolder,
        position: Int
    ) {
        val product = productList[position]
        holder.binding.apply {
            tvItemName.text = product.proName
            tvItemPrice.text = "PKR. ${product.proPrice}"

        }
    }

    override fun getItemCount(): Int {
         return productList.size
    }


}