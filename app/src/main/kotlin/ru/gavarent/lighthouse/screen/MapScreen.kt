package ru.gavarent.lighthouse.screen

import android.os.Bundle
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolylineOptions
import com.google.maps.android.ktx.awaitMap
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getComposeViewModelOwner
import org.koin.androidx.compose.getViewModel
import ru.gavarent.lighthouse.R
import ru.gavarent.lighthouse.ui.theme.AppTheme

@Composable
fun MapScreen(navigateUp: () -> Boolean = { true }) {
    val composeViewModelOwner = getComposeViewModelOwner()
    val viewModel = getViewModel<MapViewModel>()
    androidx.compose.material.Surface(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            val mapView = rememberMapViewWithLifecycle()
            AndroidView({ mapView }) {

                CoroutineScope(Dispatchers.Main).launch {
                    val map = mapView.awaitMap()
                    map.uiSettings.isZoomControlsEnabled = true

                    val pickUp = LatLng(-35.016, 143.321)
                    val destination = LatLng(-32.491, 147.309)
                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(destination, 6f))
                    val markerOptions = MarkerOptions()
                        .title("Sydney Opera House")
                        .position(pickUp)
                    map.addMarker(markerOptions)

                    val markerOptionsDestination = MarkerOptions()
                        .title("Restaurant Hubert")
                        .position(destination)
                    map.addMarker(markerOptionsDestination)

                    map.addPolyline(
                        PolylineOptions().add(
                            pickUp,
                            LatLng(-34.747, 145.592),
                            LatLng(-34.364, 147.891),
                            LatLng(-33.501, 150.217),
                            LatLng(-32.306, 149.248),
                            destination
                        )
                    )
                }
            }
        }
    }

}

@Composable
fun rememberMapViewWithLifecycle(): MapView {
    val context = LocalContext.current
    val mapView = remember {
        MapView(context).apply {
            id = R.id.map
        }
    }

    // Makes MapView follow the lifecycle of this composable
    val lifecycleObserver = rememberMapLifecycleObserver(mapView)
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    DisposableEffect(lifecycle) {
        lifecycle.addObserver(lifecycleObserver)
        onDispose {
            lifecycle.removeObserver(lifecycleObserver)
        }
    }

    return mapView
}

@Composable
fun rememberMapLifecycleObserver(mapView: MapView): LifecycleEventObserver =
    remember(mapView) {
        LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_CREATE -> mapView.onCreate(Bundle())
                Lifecycle.Event.ON_START -> mapView.onStart()
                Lifecycle.Event.ON_RESUME -> mapView.onResume()
                Lifecycle.Event.ON_PAUSE -> mapView.onPause()
                Lifecycle.Event.ON_STOP -> mapView.onStop()
                Lifecycle.Event.ON_DESTROY -> mapView.onDestroy()
                else -> throw IllegalStateException()
            }
        }
    }


@Preview(showBackground = true)
@Composable
fun DefaultLightPreview() {
    AppTheme(useDarkTheme = false) {
        MapScreen()
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultDarkPreview() {
    AppTheme(useDarkTheme = true) {
        MapScreen()
    }
}