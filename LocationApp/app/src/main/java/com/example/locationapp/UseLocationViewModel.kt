package com.example.locationapp

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class UseLocationViewModel: ViewModel() {
    private var _location = mutableStateOf<UserLocation?>(null)

    var location: State<UserLocation?> = _location


    fun setLocation(userLocation: UserLocation) {
        _location.value = userLocation
    }

}