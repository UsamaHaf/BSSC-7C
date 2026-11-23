package com.techwarsol.testapp.Model

    data class UserResponse(
        val users: ArrayList<ApiUser>,
        val total: Int,
        val skip: Int,
        val limit: Int
    )

    data class ApiUser(
        val id: Int,
        val firstName: String,
        val lastName: String,
        val email: String,
        val phone: String,
        val image: String,
        val company: Company,
    )

    data class Company(
        val name: String,
        val title: String
    )


