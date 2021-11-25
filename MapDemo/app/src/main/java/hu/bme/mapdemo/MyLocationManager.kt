package hu.bme.mapdemo

import android.content.Context
import android.location.Location
import android.os.Looper
import com.google.android.gms.location.*

class MyLocationManager(context: Context,
                        private val onNewLocationAvailable: OnNewLocationAvailable) {

    interface OnNewLocationAvailable {
        fun onNewLocation(location: Location)
    }

    private val fusedLocationClient: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(context)

    private val locationCallback: LocationCallback = object : LocationCallback() {
        override fun onLocationResult(locResult: LocationResult) {
            super.onLocationResult(locResult)
            onNewLocationAvailable.onNewLocation(locResult.lastLocation)
        }
    }

    @Throws(SecurityException::class)
    fun startLocationMonitoring() {
        val locationRequest = LocationRequest.create()
        locationRequest.interval = 1000
        locationRequest.fastestInterval = 1000
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        locationRequest.smallestDisplacement = 10f

        fusedLocationClient.requestLocationUpdates(locationRequest,
            locationCallback, Looper.getMainLooper())
    }

    @Throws(SecurityException::class)
    fun stopLocationMonitoring() {
        fusedLocationClient.removeLocationUpdates(locationCallback)
    }

}