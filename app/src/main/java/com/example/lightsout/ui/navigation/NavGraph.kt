package com.example.lightsout.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.lightsout.ui.screens.GameScreen
import com.example.lightsout.ui.screens.PlayGuideScreen
import com.example.lightsout.ui.screens.SettingScreen


@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "setting") {
        composable("setting") {
            SettingScreen(
                navController,
                onStartGame = { minMass ->
                    navController.navigate("game/$minMass")
                },
            )
        }
        composable("game/{minMass}", arguments = listOf(navArgument("minMass") { type = NavType.IntType })) { backStackEntry ->
            val minMass = backStackEntry.arguments?.getInt("minMass") ?: 0
            GameScreen(
                navController,
                minMass = minMass
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
