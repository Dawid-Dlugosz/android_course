package com.example.locationapp

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import android.Manifest
import android.app.Activity
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat
import androidx.core.location.LocationCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.locationapp.ui.theme.LocationAppTheme
import com.google.android.libraries.searchinapps.LocationContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val locationViewModel: UseLocationViewModel = viewModel()
            LocationAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyApp(locationViewModel)
                }
            }
        }
    }
}

@Composable
fun MyApp(viewModel: UseLocationViewModel) {
    val context = LocalContext.current
    val locationHelper = LocationHelper(context = context)

    LocationApp(
        context = context,
        viewModel = viewModel,
        locationHelper = locationHelper
    )
}

@Composable
fun LocationApp(
     context: Context,
     viewModel: UseLocationViewModel,
     locationHelper: LocationHelper
){
    val location = viewModel.location
    val address = location.value?.let {
        locationHelper.reverseGeocodeLocation(it)
    }

    val locationPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions(),
        onResult = {
            permission ->
            if(permission[Manifest.permission.ACCESS_COARSE_LOCATION] == true && permission[Manifest.permission.ACCESS_FINE_LOCATION] == true) {
                locationHelper.requestLocationUpdate(viewModel = viewModel)
            } else{
                val rationalRequired = ActivityCompat.shouldShowRequestPermissionRationale(
                    context as MainActivity,
                    Manifest.permission.ACCESS_COARSE_LOCATION)
                 || ActivityCompat.shouldShowRequestPermissionRationale(
                    context,
                    Manifest.permission.ACCESS_COARSE_LOCATION)

                if(rationalRequired) {
                    Toast.makeText(context, "Permissions are required for this feature", Toast.LENGTH_SHORT).show()
                } else{
                    Toast.makeText(context, "Permissions are required please enable it in android settings", Toast.LENGTH_SHORT).show()
                }
            }
        }
    )


    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        if(location.value != null) {
            Text(text = "User location: ${location.value!!.latitude}  , ${location.value!!.latitude} ")
        }else {
            Text(text = "Location no avaliable")
        }

        if(address != null) {
            Text(text = "User address: $address")
        }

        Button(
            onClick = {
                if(locationHelper.hasLocationPermission(context = context)) {
                    locationHelper.requestLocationUpdate(viewModel = viewModel)
                } else{
                    locationPermissionLauncher.launch(
                        arrayOf(
                            Manifest.permission.ACCESS_COARSE_LOCATION,
                            Manifest.permission.ACCESS_FINE_LOCATION,
                        )
                    )
                }
            }
        ) {
            Text(text = "Get location")
        }
    }
}
