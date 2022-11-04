package com.artemissoftware.stormyweather.domain.repositories

import com.artemissoftware.stormyweather.domain.models.weather.WeatherInfo
import com.artemissoftware.stormyweather.domain.util.Resource

interface WeatherRepository {
    suspend fun getWeatherData(lat: Double, long: Double): Resource<WeatherInfo>
}