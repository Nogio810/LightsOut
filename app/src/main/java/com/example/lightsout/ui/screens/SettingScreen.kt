package com.example.lightsout.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.lightsout.data.DataSource
import com.example.lightsout.ui.components.HelpIconButton
import com.example.lightsout.ui.viewmodel.LightsOutScreenView

@Composable
fun SettingScreen(
    navController: NavController,
    onStartGame: (Int) -> Unit,
    viewModel: LightsOutScreenView
) {
    Log.d("LightsOutGame", "Entered SettingScreen")
    LaunchedEffect(Unit) {
        viewModel.resetHomeScreenStates()
    }

    val uiState by viewModel.uiState.collectAsState()
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp
    val minMass = screenWidth / 48

    val shouldStartGame by viewModel.shouldStartGame.collectAsState()

    Log.d("LightsOutGame", "Entered SettingScreen shouldStartGame: $shouldStartGame")

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 8.dp)
                .align(Alignment.TopCenter)
                .padding(WindowInsets.statusBars.asPaddingValues())
                .padding(WindowInsets.navigationBars.asPaddingValues())
        ) {
            item {
                MassNumberSetting(
                    isNumberWrong = uiState.isNumberWrong,
                    rowNum = uiState.inputString,
                    onRowNumChanged = {
                        Log.d("LightsOutGame", "onStartGame called with number: $it")
                        viewModel.checkUserNumber(it, minMass)
                    },
                    minMass = minMass,
                    difficultyOptions = DataSource.difficulties.map { id ->
                        LocalContext.current.getString(
                            id
                        )
                    },
                    selectedDifficulty = uiState.difficulty,
                    onDifficultyChanged = { viewModel.setDifficulties(it) },
                    onStartGame = {
                        viewModel.triggerGameStart()
                    }
                )
            }
        }
        HelpIconButton(
            onClick = {
                navController.navigate("playGuide/$minMass")
            },
            modifier = Modifier
                .align(Alignment.TopEnd)
        )
    }

    LaunchedEffect(shouldStartGame) {
        Log.d("LightsOutGame", "LaunchedEffect triggered - shouldStartGame: $shouldStartGame, rowNum: ${uiState.rowNum}")
        if(shouldStartGame && uiState.rowNum > 2 && uiState.difficulty.isNotEmpty()){
            viewModel.resetGameStartFlag()
            Log.d("LightsOutGame", "START")
            onStartGame(minMass)
        }else{
            viewModel.resetGameStartFlag()
        }
    }

    LaunchedEffect(uiState.rowNum) {
        Log.d("LightsOutGame", "rowNum changed - new value: ${uiState.rowNum}")
    }
}