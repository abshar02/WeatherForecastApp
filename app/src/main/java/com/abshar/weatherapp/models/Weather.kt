package com.abshar.weatherapp.models

import com.google.gson.annotations.SerializedName


data class Weather(
        @SerializedName("main") var main: String,
        @SerializedName("description") var description: String,
        @SerializedName("icon") var icon: String
)