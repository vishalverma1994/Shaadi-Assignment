package com.assingment.data.model

import androidx.room.Embedded

data class LocationModel(
    @Embedded
    val street: StreetModel? = null,

    val city: String = "",
    val state: String = "",
    val country: String = "",
    val postcode: String = "",
    @Embedded
    val coordinates: CoordinatesModel? = null,
    @Embedded
    val timezone: TimezoneModel? = null
)



