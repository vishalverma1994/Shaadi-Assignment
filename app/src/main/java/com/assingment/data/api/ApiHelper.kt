package com.assingment.data.api

import com.assingment.data.model.response.UserListResponse
import retrofit2.Response

interface ApiHelper {

    suspend fun requestUserList(resultNumber: Int): Response<UserListResponse>
}