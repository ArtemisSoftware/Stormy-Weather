package com.artemissoftware.stormyweather.presentation.weather

import com.artemissoftware.stormyweather.domain.models.weather.WeatherInfo

data class WeatherState(
    val weatherInfo: WeatherInfo? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)