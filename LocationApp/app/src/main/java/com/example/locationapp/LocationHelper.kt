package com.example.locationapp

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.location.Address
import android.location.Geocoder
import android.os.Looper
import androidx.core.content.ContextCompat
import androidx.core.content.PermissionChecker
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.libraries.searchinapps.LocationContext.LatLng
import java.util.Locale

class LocationHelper(val context: Context) {

    private val _fusedLocationProvider = LocationServices.getFusedLocationProviderClient(context)


    @SuppressLint("MissingPermission")
    fun requestLocationUpdate(viewModel: UseLocationViewModel) {
        val locationCallback =  object : LocationCallback() {
            override fun onLocationResult(locationRequest: LocationResult) {
                super.onLocationResult(locationRequest)
                locationRequest.lastLocation?.let {
                    val userLocation = UserLocation(
                        latitude = it.latitude,
                        longitude = it.longitude
                    )

                    viewModel.setLocation(userLocation = userLocation)
                }
            }
        }
        val locationRequest = LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, 1000).build()

        _fusedLocationProvider.requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper())
    }

    fun hasLocationPermission(context: Context): Boolean {
        return  ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PermissionChecker.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) == PermissionChecker.PERMISSION_GRANTED
    }

    fun reverseGeocodeLocation(userLocation: UserLocation): String {
        val geocoder = Geocoder(context, Locale.getDefault())
        val addresses: MutableList<Address>? = geocoder.getFromLocation(userLocation.latitude, userLocation.longitude, 1)

        return if(!addresses.isNullOrEmpty()) {
            addresses[0].getAddressLine(0)
        }else {
            "No address found"
        }
    }
}