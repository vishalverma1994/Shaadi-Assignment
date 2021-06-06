package com.assingment.data.model

import com.google.gson.annotations.SerializedName

data class DateOfBirthModel(
    @SerializedName("date")
    val date: String = "",
    @SerializedName("age")
    val age: Int = 0
)
