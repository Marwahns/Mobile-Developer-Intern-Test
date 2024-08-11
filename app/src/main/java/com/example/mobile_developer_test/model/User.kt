//Marwah Nur Shafira
package com.example.mobile_developer_test.model

data class User(
    val id: Int,
    val email: String,
    val first_name: String,
    val last_name: String,
    val avatar: String
)

data class UserResponse(
    val page: Int,
    val per_page: Int,
    val total: Int,
    val total_pages: Int,
    val data: List<User>,
    val support: Support
)

data class Support(
    val url: String,
    val text: String
)

