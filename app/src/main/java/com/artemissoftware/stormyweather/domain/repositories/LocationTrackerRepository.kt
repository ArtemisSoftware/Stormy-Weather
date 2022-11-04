package com.artemissoftware.stormyweather.domain.repositories

import android.location.Location

interface LocationTrackerRepository {
    suspend fun getCurrentLocation(): Location?
}