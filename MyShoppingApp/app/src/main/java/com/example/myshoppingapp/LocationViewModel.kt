package com.example.myshoppingapp

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.lang.Exception

class LocationViewModel: ViewModel() {

    private val _location = mutableStateOf<LocationData?>(null)
    val location: State<LocationData?> = _location

    private val _address = mutableStateOf(listOf<GeocodingResult>())
    val address: State<List<GeocodingResult>> = _address

    fun updateLocation(locationData: LocationData) {
        _location.value = locationData
    }

    fun fetchAddress(latlng: String) {
        try {
            viewModelScope.launch {
                val result = RetrofitClient.create().getAddressFromCoordinates(
                    latLng = latlng,
                    apiKey = "AIzaSyBMEpY2BIxyP2RLA7pe0mVrvytmbPntRIQ"
                )

                _address.value = result.results

            }
        } catch (e: Exception) {
            Log.d("req1", "${e.cause} ${e.message}")
        }
    }
}