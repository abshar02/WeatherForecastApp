package com.abshar.weatherapp.models

import com.google.gson.annotations.SerializedName

data class Temperature(
        @SerializedName("day") var atDay: Double,
        @SerializedName("night") var atNight: Double
)