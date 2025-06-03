package com.example.lightsout.ui.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.lightsout.ui.screens.GameScreen
import com.example.lightsout.ui.screens.PlayGuideScreen
import com.example.lightsout.ui.screens.SettingScreen
import com.example.lightsout.ui.viewmodel.LightsOutScreenView


@Composable
fun NavGraph(
    navController: NavHostController,
    lightsOutScreenView: LightsOutScreenView
) {
    LaunchedEffect(navController) {
        navController.currentBackStackEntryFlow.collect { entry ->
            val route = entry.destination.route
            val currentDestination = navController.currentDestination?.route
            val previousDestination = navController.previousBackStackEntry?.destination?.route
            Log.d(
                "LightsOutGame",
                "Backstack: $route, Current Destination: $currentDestination, Previous Destination: $previousDestination"
            )
        }
    }

    NavHost(navController = navController, startDestination = "setting") {
        composable("setting") {
            SettingScreen(
                navController,
                onStartGame = { minMass ->
                    Log.d("LightsOutGame", "onStartGame called with minMass: $minMass")
                    navController.navigate("game/$minMass")
                    Log.d("LightsOutGame", "Navigating to GameScreen with minMass: $minMass")
                },
                lightsOutScreenView
            )
        }
        composable("game/{minMass}", arguments = listOf(navArgument("minMass") { type = NavType.IntType })) { backStackEntry ->
            val minMass = backStackEntry.arguments?.getInt("minMass") ?: 0
            GameScreen(
                navController,
                minMass = minMass,
                lightsOutScreenView = lightsOutScreenView
            )
        }
        composable("playGuide/{minMass}") { backStackEntry ->
            val minMass = backStackEntry.arguments?.getString("minMass")?.toIntOrNull() ?: 0
            PlayGuideScreen(
                navController,
                minMass = minMass
            )
        }
    }
}
