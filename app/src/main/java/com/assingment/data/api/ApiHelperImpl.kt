package com.assingment.data.api

import com.assingment.data.model.response.UserListResponse
import retrofit2.Response

class ApiHelperImpl(private val apiService: ApiService) : ApiHelper {
    override suspend fun requestUserList(resultNumber: Int): Response<UserListResponse> = apiService.requestUserList(resultNumber)

    /*override suspend fun requestUserDetails(userId: Int): Response<UserDataModel> = apiService.requestUserDetails(userId)*/

}