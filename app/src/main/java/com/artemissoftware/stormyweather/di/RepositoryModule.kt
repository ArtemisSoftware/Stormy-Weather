package com.artemissoftware.stormyweather.di

import com.artemissoftware.stormyweather.data.repositories.LocationTrackerRepositoryImpl
import com.artemissoftware.stormyweather.data.repositories.WeatherRepositoryImpl
import com.artemissoftware.stormyweather.domain.repositories.LocationTrackerRepository
import com.artemissoftware.stormyweather.domain.repositories.WeatherRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {


    @Binds
    @Singleton
    abstract fun bindLocationTracker(locationTrackerRepositoryImpl: LocationTrackerRepositoryImpl): LocationTrackerRepository

    @Binds
    @Singleton
    abstract fun bindWeatherRepository(weatherRepositoryImpl: WeatherRepositoryImpl): WeatherRepository
}