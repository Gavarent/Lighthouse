package ru.gavarent.lighthouse

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import ru.gavarent.lighthouse.screen.MapScreen
import ru.gavarent.lighthouse.screen.root.Screen

@ExperimentalAnimationApi
@Composable
internal fun AppNavigation(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    AnimatedNavHost(
        navController = navController,
        startDestination = Screen.Welcome.route,
//        enterTransition = { initial, target -> defaultTiviEnterTransition(initial, target) },
//        exitTransition = { initial, target -> defaultTiviExitTransition(initial, target) },
//        popEnterTransition = { _, _ -> defaultTiviPopEnterTransition() },
//        popExitTransition = { _, _ -> defaultTiviPopExitTransition() },
        modifier = modifier,
    ) {
        composable(Screen.Welcome.route) {
            Surface(modifier = modifier) {
                Text(text = "Welcome Screen")
            }
        }
        composable(Screen.Map.route) {
            MapScreen(navController::navigateUp)
        }

    }
}