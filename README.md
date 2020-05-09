# WeatherApp-Kotlin

Description

This is a simple android weather app developed in kotlin, demonstrating the use of  Dagger, Retrofit and implementing MVP.

It parses and displays weather data that is retrieved through OpenWeatherMap's API. 

Kotlin is used heavily to develop this application.

The main goal of this app is to be a sample of how to build an high quality Android application that uses the Architecture components, Dagger etc. in Kotlin.


Build the project

1) Clone or download the project

General flow of data

Check if there is cached data present in the internal file, if yes then load the cached data.
Retrieve the latitude and longitude of the user.
Request data from Weather Api
If data received, cache it in internal file and show the updated data to user.
If error then notify user about it.
