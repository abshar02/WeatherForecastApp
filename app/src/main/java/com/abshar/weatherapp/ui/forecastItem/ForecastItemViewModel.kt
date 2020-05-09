package com.abshar.weatherapp.ui.forecastItem

import com.abshar.weatherapp.models.Temperature

data class ForecastItemViewModel(
        val temperature: Temperature,
        val icon: String = "01d",
        val date: Long = System.currentTimeMillis(),
        val description: String = ""
)