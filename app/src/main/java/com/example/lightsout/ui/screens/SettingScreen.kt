package com.example.lightsout.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.lightsout.data.DataSource
import com.example.lightsout.ui.components.HelpIconButton
import com.example.lightsout.ui.viewmodel.LightsOutScreenView

@Composable
fun SettingScreen(
    navController: NavController,
    onStartGame: (Int) -> Unit,
    viewModel: LightsOutScreenView = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp
    val minMass = screenWidth / 48

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .align(Alignment.TopCenter)
                .padding(WindowInsets.navigationBars.asPaddingValues())
        ) {
            item {
                MassNumberSetting(
                    isNumberWrong = uiState.isNumberWrong,
                    userNumber = viewModel.userNumber,
                    onUserNumberChanged = { viewModel.updateUserNumber(it) },
                    minMass = minMass,
                    difficultyOptions = DataSource.difficulties.map { id ->
                        LocalContext.current.getString(
                            id
                        )
                    },
                    onDifficultyChanged = { viewModel.setDifficulties(it) },
                    onStartGame = {
                        viewModel.checkUserNumber(minMass)
                        viewModel.updateGameScreen()
                        onStartGame(minMass)
                    }
                )
            }
        }
        HelpIconButton(
            onClick = {
                navController.navigate("playGuide")
            },
            modifier = Modifier
                .align(Alignment.TopEnd)
        )
    }
}