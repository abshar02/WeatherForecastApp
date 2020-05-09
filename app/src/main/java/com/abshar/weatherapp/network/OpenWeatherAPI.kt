package com.abshar.weatherapp.network

import com.abshar.weatherapp.network.responses.ForecastResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherAPI {
    @GET("forecast/daily/")
    fun getForecast(@Query("q") city: String, @Query("cnt") days: Int): Call<ForecastResponse>

    companion object {
        const val BASE_URL = "https://api.openweathermap.org/data/2.5/"
    }
}