package com.example.lightsout.ui.screens

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.navigation.NavController
import com.example.lightsout.ui.components.GameContent
import com.example.lightsout.ui.components.GameTopBar
import com.example.lightsout.ui.game.ClearOverlay
import com.example.lightsout.ui.viewmodel.LightsOutScreenView

@Composable
fun GameScreen(
    navController: NavController,
    lightsOutScreenView: LightsOutScreenView,
    minMass: Int,
    modifier: Modifier = Modifier
) {
    Log.d("LightsOutGame", "GameScreen composed")


    val uiState by lightsOutScreenView.uiState.collectAsState()
    if (uiState.rowNum == 0) {
        Box(modifier = Modifier.fillMaxSize().background(colorScheme.background))
        return
    }

    LaunchedEffect(Unit) {
        val currentDestination = navController.currentBackStackEntry?.destination?.route
        Log.d("LightsOutGame", "Entered GameScreen, current destination: $currentDestination")
    }

    SideEffect {
        Log.d("LightsOutGame", "GameScreen re-composed")
    }

    BackHandler {
        navController.popBackStack()
    }

    val lightsOutUiState by lightsOutScreenView.uiState.collectAsState()

    Log.d("LightsOutGame", "GameScreen - rowNum: ${lightsOutUiState.rowNum}")

    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp
    val massSize = screenWidth / lightsOutUiState.rowNum
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = colorScheme.background)
    ) {
        GameTopBar(
            navController = navController,
            minMass = minMass,
            lightsOutScreenView = lightsOutScreenView,
            modifier = Modifier.align(Alignment.TopEnd)
        )
        GameContent(
            uiState = lightsOutUiState,
            massSize = massSize,
            lightsOutScreenView = lightsOutScreenView,
            modifier = Modifier.align(Alignment.Center)
        )
        ClearOverlay(
            isShowClear = lightsOutUiState.isShowClear,
            onRestart = {
                navController.navigate("setting") {
                    popUpTo("setting") { inclusive = true }
                    launchSingleTop = true
                }
            },
            modifier = Modifier.align(Alignment.Center)
        )
    }
}