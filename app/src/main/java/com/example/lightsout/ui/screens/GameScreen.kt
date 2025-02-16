package com.example.lightsout.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.lightsout.ui.components.GameContent
import com.example.lightsout.ui.components.GameTopBar
import com.example.lightsout.ui.game.ClearOverlay
import com.example.lightsout.ui.viewmodel.LightsOutScreenView

@Composable
fun GameScreen(
    navController: NavController,
    lightsOutScreenView: LightsOutScreenView = viewModel(),
    minMass: Int,
    modifier: Modifier = Modifier
) {
    BackHandler {
        navController.popBackStack()
    }

    val lightsOutUiState by lightsOutScreenView.uiState.collectAsState()

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
            onRestart = { lightsOutScreenView.resetHomeScreenStates() },
            modifier = Modifier.align(Alignment.Center)
        )
    }
}