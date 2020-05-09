# WeatherApp-Kotlin

Description

This is a simple android weather app developed in kotlin, demonstrating the use of  Dagger, Retrofit and implementing MVP.

It parses and displays weather data that is retrieved through OpenWeatherMap's API. 

Kotlin is used heavily to develop this application.

The main goal of this app is to be a sample of how to build an high quality Android application that uses the Architecture components, Dagger etc. in Kotlin.


Build the project

1)  Clone or download the project
2)  I have used Android Studio 3.6.3 latest version
3)  Run the project in Android Studio
4)  After Successfull run Weather App icon will be installed in mobile or emulator.
5)  After opening the app there is search bar where we can search any city/country name and get the weather forecast        accordingly.
6) We can see the next 9 days weather forecast.
7) In the downside we can click to get current location weather.

General flow of data

1) Check if there is cached data present in the internal file, if yes then load the cached data.
2) Retrieve the latitude and longitude of the user.
3) Request data from Weather Api
4) If data received, cache it in internal file and show the updated data to user.
5) If error then notify user about it.
