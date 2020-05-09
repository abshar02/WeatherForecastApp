package com.abshar.weatherapp.ui.main

import com.abshar.weatherapp.ui.base.BaseView
import com.abshar.weatherapp.ui.forecastItem.ForecastItemViewModel

interface MainView : BaseView {
    fun startLoading()
    fun finishLoading()
    fun updateForecast(forecasts: List<ForecastItemViewModel>)
    fun showError(errorType: ErrorTypes)
    fun showPermissionLostDialog()
    fun setCity(city: String)
}