package com.artemissoftware.stormyweather

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.artemissoftware.stormyweather.presentation.weather.WeatherScreen
import com.artemissoftware.stormyweather.ui.theme.StormyWeatherTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            StormyWeatherTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    WeatherScreen()
                }
            }
        }
    }


//    private val viewModel: WeatherViewModel by viewModels()
//    private lateinit var permissionLauncher: ActivityResultLauncher<Array<String>>
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        permissionLauncher = registerForActivityResult(
//            ActivityResultContracts.RequestMultiplePermissions()
//        ) {
//            viewModel.loadWeatherInfo()
//        }
//        permissionLauncher.launch(arrayOf(
//            Manifest.permission.ACCESS_FINE_LOCATION,
//            Manifest.permission.ACCESS_COARSE_LOCATION,
//        ))
//        setContent {
//            StormyWeatherTheme {
//                Box(
//                    modifier = Modifier.fillMaxSize()
//                ) {
//                    Column(
//                        modifier = Modifier
//                            .fillMaxSize()
//                            .background(DarkBlue)
//                    ) {
//                        WeatherCard(
//                            state = viewModel.state,
//                            backgroundColor = DeepBlue
//                        )
//                        Spacer(modifier = Modifier.height(16.dp))
//                        WeatherForecast(state = viewModel.state)
//                    }
//                    if(viewModel.state.isLoading) {
//                        CircularProgressIndicator(
//                            modifier = Modifier.align(Alignment.Center)
//                        )
//                    }
//                    viewModel.state.error?.let { error ->
//                        Text(
//                            text = error,
//                            color = Color.Red,
//                            textAlign = TextAlign.Center,
//                            modifier = Modifier.align(Alignment.Center)
//                        )
//                    }
//                }
//            }
//        }
//    }


}




