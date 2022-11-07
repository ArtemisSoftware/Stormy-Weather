package com.artemissoftware.stormyweather.presentation.weather

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.artemissoftware.stormyweather.presentation.weather.composables.WeatherCard
import com.artemissoftware.stormyweather.presentation.weather.composables.WeatherForecast
import com.artemissoftware.stormyweather.ui.theme.DarkBlue
import com.artemissoftware.stormyweather.ui.theme.DeepBlue

@Composable
fun WeatherScreen(viewModel: WeatherViewModel = hiltViewModel()) {

    val permissionGranted = remember {
        mutableStateOf(false)
    }

//    LaunchedEffect(key1 = true){
//        if(permissionGranted.value) {
//            viewModel.loadWeatherInfo()
//        }
//    }


    CheckLocationPermissions(
        onPermissionGranted =  {
           // permissionGranted.value = true
            if(it) {
                viewModel.loadWeatherInfo()
            }
            permissionGranted.value = it
        },

        permissionGranted = permissionGranted.value
    )

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(DarkBlue)
        ) {
            WeatherCard(
                state = viewModel.state,
                backgroundColor = DeepBlue
            )
            Spacer(modifier = Modifier.height(16.dp))
            WeatherForecast(state = viewModel.state)
        }
        if(viewModel.state.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }
        viewModel.state.error?.let { error ->
            Text(
                text = error,
                color = Color.Red,
                textAlign = TextAlign.Center,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }

}

@Composable
fun CheckLocationPermissions(
    onPermissionGranted: (Boolean) -> Unit,
    permissionGranted: Boolean
) {

    val context = LocalContext.current

    val permissions = arrayOf(
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.ACCESS_FINE_LOCATION
    )
    val launcherMultiplePermissions = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissionsMap ->
        val areGranted = permissionsMap.values.reduce { acc, next -> acc && next }
        if (areGranted) {
            // Use location
            onPermissionGranted.invoke(true)
        } else {
            onPermissionGranted.invoke(true) // I dont like this too, but on tutorial it suggested view model will deal with it.
            // Show dialog
        }
    }

    if(!permissionGranted) {
        CheckAndRequestLocationPermissions(
            context,
            permissions,
            launcherMultiplePermissions,
            onPermissionGranted = onPermissionGranted
        )
    }
}

@Composable
fun CheckAndRequestLocationPermissions(
    context: Context,
    permissions: Array<String>,
    launcher: ManagedActivityResultLauncher<Array<String>, Map<String, Boolean>>,
    onPermissionGranted: (Boolean) -> Unit
) {
    if (
        permissions.all {
            ContextCompat.checkSelfPermission(
                context,
                it
            ) == PackageManager.PERMISSION_GRANTED
        }
    ) {
        onPermissionGranted.invoke(true)
        // Use location because permissions are already granted
    } else {
        // Request permissions
        SideEffect {
            launcher.launch(permissions)
        }

    }
}