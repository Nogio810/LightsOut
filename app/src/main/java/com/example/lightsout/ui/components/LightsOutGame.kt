package com.example.lightsout.ui.components

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
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

    val grid = result.grid
    val newIndex = result.newIndex
    val newAnswerIndex = result.newAnswerIndex

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

    LaunchedEffect(key1 = restartTime) {
        lightsOutScreenView.setQuestionStates(
            newIndex = result.newIndex,
            newAnswerIndex = result.newAnswerIndex,
            newGrid = result.grid
        )
    }

    DisplayMass(
        massNumber = massNumber,
        gridState = grid,
        massSize = massSize,
        answerList = answerIndent,
        answer = answer,
        onCellClicked = { row, col, massNum ->
            lightsOutScreenView.onCellClicked(
                row,
                col,
                massNum
            )
        }
    )
}