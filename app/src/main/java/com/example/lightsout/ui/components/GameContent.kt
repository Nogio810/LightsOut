package com.example.lightsout.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.lightsout.R
import com.example.lightsout.ui.viewmodel.LightsOutScreenView
import com.example.lightsout.ui.viewmodel.LightsOutUiState

@Composable
fun GameContent(
    uiState: LightsOutUiState,
    massSize: Int,
    lightsOutScreenView: LightsOutScreenView,
    modifier: Modifier = Modifier
){
    val clickText = stringResource(R.string.click_Times, uiState.clickTimes)
    val answerClickText = if (uiState.isShowHints) {
        stringResource(R.string.answer_click_times, uiState.answerIndent.size)
    } else null

    Column(modifier = modifier) {
        ClickTimes(
            clickText = clickText,
            answerClickText = answerClickText
        )
        LightsOutGame(
            uiState = uiState,
            lightsOutScreenView = lightsOutScreenView,
            massSize = massSize
        )
    }
}