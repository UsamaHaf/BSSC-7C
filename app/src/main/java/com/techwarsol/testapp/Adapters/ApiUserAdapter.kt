package com.techwarsol.testapp.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import com.techwarsol.testapp.Model.ApiUser
import com.techwarsol.testapp.Model.UserResponse
import com.techwarsol.testapp.R
import com.techwarsol.testapp.databinding.ListItemApiUserBinding

class ApiUserAdapter (private var userList: ArrayList<ApiUser>):
    RecyclerView.Adapter<ApiUserAdapter.UserViewHolder>() {

    class UserViewHolder(val binding: ListItemApiUserBinding):
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UserViewHolder {
        val binding = ListItemApiUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    //this method will bind data from server
    override fun onBindViewHolder(
        holder: UserViewHolder,
        position: Int
    ) {
        val user = userList[position]

        holder.binding.tvFullName.text = "${user.firstName} ${user.lastName}"
        holder.binding.tvEmail.text = user.email
        holder.binding.tvJobTitle.text = "${user.company.title} • ${user.company.name}"

        Glide.with(holder.itemView.context)
            .load(user.image)
            .circleCrop()
            .placeholder(R.drawable.logout)
            .error(R.drawable.account)
            .into(holder.binding.ivUserAvatar)

    }

    override fun getItemCount(): Int {
        return userList.size
    }


}