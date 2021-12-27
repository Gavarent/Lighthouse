package ru.gavarent.lighthouse.screen.root

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import ru.gavarent.lighthouse.AppNavigation
import ru.gavarent.lighthouse.ui.theme.AppTheme

internal sealed class Screen(val route: String) {
    object Welcome : Screen("welcome")
    object Map : Screen("map")
    object SeaTypeDescription : Screen("seaTypeDescription/{?}")
}


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun RootScreen() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        val navController = rememberAnimatedNavController()
      
//        Button(onClick = {   navController.navigate(Screen.Map.route) }) {
//            Text(text = "Button")
//        }
        AppNavigation(
            navController = navController,
//            onOpenSettings = onOpenSettings,
            modifier = Modifier
                .fillMaxSize()
//                .weight(1f)
//                .fillMaxHeight(),
        )
        navController.navigate(Screen.Map.route)
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultLightPreview() {
    AppTheme(useDarkTheme = false) {
        Greeting("Android")
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultDarkPreview() {
    AppTheme(useDarkTheme = true) {
        Greeting("Android")
    }
}