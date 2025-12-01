package com.example.lightsout.ui.components

import android.util.Log
import androidx.compose.runtime.Composable
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
){
    val massNumber = uiState.rowNum
    val difficulty = uiState.difficulty
    val index = uiState.indent
    val generated = uiState.alreadyGenerated
    val restartTime = uiState.restartTime
    val restartBool = uiState.restartBool
    val answer = uiState.isShowAnswer
    val answerIndent = uiState.answerIndent
    val backCard = uiState.backCard


    val grid = remember(restartTime) {
        questionGeneration(
            difficulty = difficulty,
            massNumber = massNumber,
            index = index,
            generated = generated,
            questionGenerated = { lightsOutScreenView.alreadyQuestionGenerated() },
            answerIndex = answerIndent,
            increaseBackCard = { lightsOutScreenView.increaseBuckCard() },
            decreaseBackCard = { lightsOutScreenView.decreaseBuckCard() },
            restarted = { lightsOutScreenView.restarted() }
        )
    }
    Log.d("LightsOutGame", "backCardAlreadyGenerate:$backCard")

    when {
        backCard == 0 && !generated -> {
            Log.d("LightsOutGame", "backCard is 0 and not generated, executing specific logic.")
        }

        backCard != 0 && generated -> {
            Log.d("LightsOutGame", "backCard is not 0 and already generated, executing alternate logic.")
        }

        backCard == 0 && !restartBool -> {
            Log.d("LightsOutGame", "backCard is 0 and already generated, executing alternate logic.")
            lightsOutScreenView.checkCorrect()
        }
    }

    DisplayMass(
        massNumber = massNumber,
        initialGrid = grid,
        massSize = massSize,
        update = { lightsOutScreenView.updateClickTimes() },
        restart = restartTime,
        answerList = answerIndent,
        answer = answer,
        increaseBackCard = { lightsOutScreenView.increaseBuckCard() },
        decreaseBackCard = { lightsOutScreenView.decreaseBuckCard() }
    )
}