package com.example.lightsout.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.lightsout.ui.viewmodel.LightsOutScreenView

@Composable
fun GameTopBar(
    navController: NavController,
    minMass: Int,
    lightsOutScreenView: LightsOutScreenView,
    modifier: Modifier = Modifier
){
    Column(modifier = modifier) {
        Spacer(modifier = Modifier.windowInsetsBottomHeight(WindowInsets.systemBars))
        SettingIcon(
            navController = navController,
            minMass = minMass,
            restart = {lightsOutScreenView.restartGame()},
            answerShow = { lightsOutScreenView.answerShow() },
            showHints = { lightsOutScreenView.showHints() },
            modifier = Modifier
        )
    }
}