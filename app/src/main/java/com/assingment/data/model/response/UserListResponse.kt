package com.assingment.data.model.response

import com.assingment.data.local.entities.UserEntity

data class UserListResponse(
    val results: List<UserEntity>,
    val info: Info? = null
)


