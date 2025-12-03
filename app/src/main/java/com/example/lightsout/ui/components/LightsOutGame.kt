package com.example.lightsout.ui.components

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.lightsout.ui.game.DisplayMass
import com.example.lightsout.ui.game.questionGeneration
import com.example.lightsout.ui.viewmodel.LightsOutScreenView
import com.example.lightsout.ui.viewmodel.LightsOutUiState

@Composable
fun LightsOutGame(
    uiState: LightsOutUiState,
    lightsOutScreenView: LightsOutScreenView,
    massSize: Int
) {
    val massNumber = uiState.rowNum
    val difficulty = uiState.difficulty
    val index = uiState.indent
    val generated = uiState.alreadyGenerated
    val restartTime = uiState.restartTime
    val restartBool = uiState.restartBool
    val answer = uiState.isShowAnswer
    val answerIndent = uiState.answerIndent
    val backCard = uiState.backCard


    val result = remember(restartTime) {
        questionGeneration(
            difficulty = difficulty,
            massNumber = massNumber,
            generated = generated,
            questionGenerated = { lightsOutScreenView.alreadyQuestionGenerated() },
            setBackCardCount = { totalLights -> lightsOutScreenView.setBackCardCount(totalLights) },
            restarted = { lightsOutScreenView.restarted() }
        )
    }

    val clickTimestamp = remember { mutableStateOf(0L) }

    val stableOnCellClicked = remember(lightsOutScreenView) {
        { row: Int, col: Int, massNum: Int ->
            clickTimestamp.value = System.currentTimeMillis()
            lightsOutScreenView.onCellClicked(row, col, massNum)
        }
    }

    LaunchedEffect(key1 = restartTime) {
        lightsOutScreenView.setQuestionStates(
            newIndex = result.newIndex,
            newAnswerIndex = result.newAnswerIndex,
            newGrid = result.grid
        )
    }

    if (uiState.currentGrid.isEmpty() || massNumber == 0) {
        Log.d("LightsOutGame", "Waiting for grid initialization...")
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
        return
    }

    Log.d("LightsOutGame", "backCardAlreadyGenerate:$backCard")

    when {
        backCard == 0 && !generated -> {
            Log.d("LightsOutGame", "backCard is 0 and not generated, executing specific logic.")
        }

        backCard != 0 && generated -> {
            Log.d(
                "LightsOutGame",
                "backCard is not 0 and already generated, executing alternate logic."
            )
        }

        backCard == 0 && !restartBool -> {
            Log.d(
                "LightsOutGame",
                "backCard is 0 and already generated, executing alternate logic."
            )
            lightsOutScreenView.checkCorrect()
        }
    }

    val isGameActive = generated && !restartBool

    LaunchedEffect(uiState.currentGrid) {
        if (isGameActive && clickTimestamp.value != 0L) {
            val renderTime = System.currentTimeMillis() - clickTimestamp.value
            Log.d("LightsOutGame", "体感遅延時間:${renderTime}ms")

            clickTimestamp.value = 0L
        }
    }

    DisplayMass(
        massNumber = massNumber,
        gridState = uiState.currentGrid,
        massSize = massSize,
        answerList = answerIndent,
        answer = answer,
        onCellClicked = stableOnCellClicked
    )
}