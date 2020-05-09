package com.abshar.weatherapp.network.responses

import com.google.gson.annotations.SerializedName
import com.abshar.weatherapp.models.City
import com.abshar.weatherapp.models.Forecast

data class ForecastResponse (
        @SerializedName("city") var city: City,
        @SerializedName("list") var forecast: List<Forecast>
)