package com.example.lightsout.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.compose.LightsOutTheme

@Composable
fun GameScreenApp(
    lightsOutScreenView: LightsOutScreenView = viewModel(),
    onBackPressed: () -> Unit,
    minMass: Int,
    modifier: Modifier = Modifier
){
    BackHandler {
        onBackPressed()
    }

    val lightsOutUiState by lightsOutScreenView.uiState.collectAsState()

    val massNumber = lightsOutUiState.rowNum
    val difficulty = lightsOutUiState.difficulty

    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp
    val massSize = screenWidth / massNumber
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorScheme.background)
    ) {
        Column(modifier = modifier.align(Alignment.TopEnd)) {
            Spacer(
                modifier.windowInsetsBottomHeight(
                    WindowInsets.systemBars
                )
            )
            SettingIcon(
                onBackPressed = onBackPressed,
                restart = { lightsOutScreenView.restartGame() },
                answerShow = { lightsOutScreenView.answerShow() },
                guideShow = { lightsOutScreenView.playGuideShow() },
                guideHide = {lightsOutScreenView.playGuideHide() },
                showHints = { lightsOutScreenView.showHints() },
                showPlayGuide = lightsOutUiState.isShowPlayGuide,
                modifier = Modifier
            )
        }
        Column(
            modifier = modifier.align(Alignment.Center)
        ) {
            ClickTimes(
                clickTimes = lightsOutUiState.clickTimes,
                answerClickTimes = lightsOutUiState.answerIndent.size,
                showHints = lightsOutUiState.isShowHints,
                modifier = Modifier
            )
            LightsOutGame(
                massNumber = massNumber,
                massSize = massSize,
                difficulty = difficulty,
                index = lightsOutUiState.indent,
                update = { lightsOutScreenView.updateClickTimes() },
                generated = lightsOutUiState.alreadyGenerated,
                restart = lightsOutUiState.restart,
                answer = lightsOutUiState.isShowAnswer,
                answerIndent = lightsOutUiState.answerIndent,
                questionGenerated = { lightsOutScreenView.alreadyQuestionGenerated() },
                checkCorrect = { lightsOutScreenView.checkCorrect() },
                increaseBackCard = { lightsOutScreenView.increaseBuckCard() },
                decreaseBackCard = { lightsOutScreenView.decreaseBuckCard() },
                backCard = lightsOutUiState.backCard
            )
        }
        if (lightsOutUiState.isShowPlayGuide){
            Box(
                modifier = modifier
                    .fillMaxSize()
            ){
                ShowPlayGuide(
                    hidePlayGuide = { lightsOutScreenView.playGuideHide() },
                    minMass = minMass,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
        if(lightsOutUiState.isShowClear){
            ClearScreen(
                onClick = onBackPressed,
                modifier = Modifier.align(alignment = Alignment.Center)
            )
        }
    }
}

@Preview
@Composable
fun GameScreenPreview(){
    val lightsOutScreenView: LightsOutScreenView = viewModel()
    val onGameScreenBackPressed = { lightsOutScreenView.resetHomeScreenStates() }
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp
    val minMass: Int = screenWidth / 48
    LightsOutTheme  (darkTheme = false) {
        GameScreenApp(onBackPressed = onGameScreenBackPressed, minMass = minMass)
    }
}

@Preview
@Composable
fun GameScreenDarkThemePreview(){
    val lightsOutScreenView: LightsOutScreenView = viewModel()
    val onGameScreenBackPressed = { lightsOutScreenView.resetHomeScreenStates() }
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp
    val minMass: Int = screenWidth / 48
    LightsOutTheme(darkTheme = true) {
        GameScreenApp(onBackPressed = onGameScreenBackPressed, minMass = minMass)
    }
}