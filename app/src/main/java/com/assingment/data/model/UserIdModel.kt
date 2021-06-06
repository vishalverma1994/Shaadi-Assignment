package com.assingment.data.model

import com.google.gson.annotations.SerializedName

data class UserIdModel(
    @SerializedName("name")
    val name: String = "",
    val value: String? = "",
)
