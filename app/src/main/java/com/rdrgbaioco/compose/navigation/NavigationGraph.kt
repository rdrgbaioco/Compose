package com.rdrgbaioco.compose.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.rdrgbaioco.compose.domain.entities.User
import com.rdrgbaioco.compose.ui.screen.home.HomeScreen
import com.rdrgbaioco.compose.ui.screen.user.UserDetailsScreen
import com.rdrgbaioco.compose.ui.screen.user.UserScreen

/// The navigation graph is added in the MainActivity.kt
@Composable
fun NavigationGraph(
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        startDestination = Screens.Home.route,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None },
        popEnterTransition = { EnterTransition.None },
        popExitTransition = { ExitTransition.None },
    ) {
        composable(
            route = Screens.Home.route,
        ) {
            HomeScreen(
                navigate = { route ->
                    navController.navigate(route)
                }
            )
        }
        composable(
            route = Screens.User.route,
        ) {
            UserScreen(
                navigate = { route ->
                    navController.navigate(route)
                },
                viewDetails = { user ->
                    navController.navigate(user) {
                        launchSingleTop = true
                        restoreState = true
                        popUpTo(Screens.User.route)
                    }
                }
            )
        }
        composable<User> { backStackEntry ->
            val user: User = backStackEntry.toRoute()
            UserDetailsScreen(
                user = user,
                navigateBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}