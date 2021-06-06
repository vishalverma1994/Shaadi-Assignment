package com.assingment.data.local.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.assingment.data.model.*
import com.google.gson.annotations.SerializedName

@Entity
data class UserEntity(

    @PrimaryKey(autoGenerate = true)
    val uid: Int,

    @SerializedName("gender")
    val gender: String = "",

    @Embedded(prefix = "userNameDetails")
    @SerializedName("name")
    val name: NameModel?,

    @Embedded(prefix = "locationDetails")
    @SerializedName("location")
    val location: LocationModel?,

    @SerializedName("email")
    val email: String = "",

    @Embedded
    @SerializedName("login")
    val login: LoginModel?,

    @Embedded(prefix = "userDateOfBirth")
    @SerializedName("dob")
    val dob: DateOfBirthModel?,

    @Embedded(prefix = "userRegistered")
    @SerializedName("registered")
    val registered: DateOfBirthModel?,

    @SerializedName("phone")
    val phone: String = "",

    @SerializedName("cell")
    val cell: String = "",

    @Embedded(prefix = "userIdDetails")
    @SerializedName("id")
    val id: UserIdModel?,

    @Embedded
    @SerializedName("picture")
    val picture: PictureModel?,

    @SerializedName("nat")
    val nat: String = "",

    @SerializedName("status")
    var status: String? = ""
)