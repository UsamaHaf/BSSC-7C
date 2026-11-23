package com.techwarsol.testapp.Interfaces

import com.techwarsol.testapp.Model.UserResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface DummyApiService {

    @GET("users")
    fun getUsers(): Call<UserResponse>

}
object RetrofitClient {

    private const val BASE_URL = "https://dummyjson.com/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create()).build()

    val apiService: DummyApiService = retrofit.create(DummyApiService::class.java)

}