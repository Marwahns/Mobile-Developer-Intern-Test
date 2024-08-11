//Marwah Nur Shafira
package com.example.mobile_developer_test.controller

import com.example.mobile_developer_test.model.UserResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("api/users")
    suspend fun getUsers(
        @Query("per_page") perPage: Int,
        @Query("page") page: Int
    ): UserResponse
}
