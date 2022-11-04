package com.artemissoftware.stormyweather.data.repositories

import android.Manifest
import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import androidx.core.content.ContextCompat
import com.artemissoftware.stormyweather.domain.repositories.LocationTrackerRepository
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import com.google.android.gms.location.FusedLocationProviderClient
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlin.coroutines.resume

@ExperimentalCoroutinesApi
class LocationTrackerRepositoryImpl @Inject constructor(
    private val locationClient: FusedLocationProviderClient,
    private val application: Application
): LocationTrackerRepository {

    @SuppressLint("MissingPermission")
    override suspend fun getCurrentLocation(): Location? {

        if (hasAnyKindOfLocationAvailable()) {

            return suspendCancellableCoroutine { continuation ->
                locationClient.lastLocation.apply {
                    if (isComplete) {
                        if (isSuccessful) {
                            continuation.resume(result)
                        } else {
                            continuation.resume(null)
                        }
                        return@suspendCancellableCoroutine
                    }
                    addOnSuccessListener {
                        continuation.resume(it)
                    }
                    addOnFailureListener {
                        continuation.resume(null)
                    }
                    addOnCanceledListener {
                        continuation.cancel()
                    }
                }
            }
        }
        else return null
    }

    private fun hasAnyKindOfLocationAvailable(): Boolean{

        val hasAccessFineLocationPermission = ContextCompat.checkSelfPermission(
            application,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        val hasAccessCoarseLocationPermission = ContextCompat.checkSelfPermission(
            application,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        val locationManager = application.getSystemService(Context.LOCATION_SERVICE) as LocationManager

        val isGpsEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER) ||
                locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)

        return !(!hasAccessCoarseLocationPermission || !hasAccessFineLocationPermission || !isGpsEnabled)
    }

}