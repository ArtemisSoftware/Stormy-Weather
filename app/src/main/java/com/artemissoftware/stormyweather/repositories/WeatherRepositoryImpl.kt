package com.artemissoftware.stormyweather.repositories

import com.artemissoftware.stormyweather.data.mappers.toWeatherInfo
import com.artemissoftware.stormyweather.data.remote.WeatherApi
import com.artemissoftware.stormyweather.domain.models.WeatherInfo
import com.artemissoftware.stormyweather.domain.repositories.WeatherRepository
import com.artemissoftware.stormyweather.domain.util.Resource
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val api: WeatherApi
): WeatherRepository {

    override suspend fun getWeatherData(lat: Double, long: Double): Resource<WeatherInfo> {
        return try {
            Resource.Success(
                data = api.getWeatherData(
                    lat = lat,
                    long = long
                ).toWeatherInfo()
            )
        } catch(e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "An unknown error occurred.")
        }
    }
}