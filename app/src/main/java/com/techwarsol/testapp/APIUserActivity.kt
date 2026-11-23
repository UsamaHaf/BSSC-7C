package com.techwarsol.testapp

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.techwarsol.testapp.Adapters.ApiUserAdapter
import com.techwarsol.testapp.Model.ApiUser
import com.techwarsol.testapp.Model.UserResponse
import com.techwarsol.testapp.NetworkCall.RetrofitClient
import com.techwarsol.testapp.databinding.ActivityApiuserBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class APIUserActivity : AppCompatActivity() {

    lateinit var binding: ActivityApiuserBinding
     var userList = ArrayList<ApiUser>()
    lateinit var userAdapter : ApiUserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_apiuser)

        binding = ActivityApiuserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userAdapter = ApiUserAdapter(userList)
        binding.rvApiUser.adapter = userAdapter


        fetchUser()


    }

    private fun fetchUser() {1
        binding.pbApiUser.visibility = View.VISIBLE

        val call = RetrofitClient.apiService.getUsers()

        call.enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                binding.pbApiUser.visibility = View.GONE

                if (response.isSuccessful && response.body() != null) {
                    userList.clear()
                    userList.addAll(response.body()!!.users)
                    userAdapter.notifyDataSetChanged()

                } else {
                    Toast.makeText(
                        this@APIUserActivity,
                        "Response Error: ${response.code()}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(response: Call<UserResponse?>, p1: Throwable) {
                Toast.makeText(
                    this@APIUserActivity,
                    "Response Error: ${response.toString()}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })


    }
}