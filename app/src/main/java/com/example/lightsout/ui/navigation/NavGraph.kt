package com.example.lightsout.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
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
                onBack = { navController.popBackStack() },
                minMass = minMass
            )
        }
        composable("playGuide") {
            PlayGuideScreen(
                navController,
                onClose = { navController.popBackStack() }
            )
        }
    }
}
