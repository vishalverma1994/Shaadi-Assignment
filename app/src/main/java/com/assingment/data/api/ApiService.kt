package com.assingment.data.api

import com.assingment.data.model.response.UserListResponse
import com.assingment.utils.API_USER_LIST
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(API_USER_LIST)
    suspend fun requestUserList(@Query("results") results: Int): Response<UserListResponse>
}