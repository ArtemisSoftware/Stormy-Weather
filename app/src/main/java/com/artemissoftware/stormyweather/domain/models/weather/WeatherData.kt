package com.artemissoftware.stormyweather.domain.models.weather

import com.artemissoftware.stormyweather.domain.types.WeatherType
import java.time.LocalDateTime

data class WeatherData(
    val time: LocalDateTime,
    val temperatureCelsius: Double,
    val pressure: Double,
    val windSpeed: Double,
    val humidity: Double,
    val weatherType: WeatherType
)
