package com.example.lightsout.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.lightsout.ui.components.DifficultySelector
import com.example.lightsout.ui.components.GameImage
import com.example.lightsout.ui.components.MassInputField
import com.example.lightsout.ui.components.StartGameButton

@Composable
fun MassNumberSetting(
    isNumberWrong: Boolean,
    onUserNumberChanged: (String) ->Unit,
    userNumber: String,
    minMass: Int,
    difficultyOptions: List<String>,
    onDifficultyChanged: (String) -> Unit,
    onStartGame: () -> Unit
){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        GameImage()
        MassInputField(
            minMass = minMass,
            isNumberWrong = isNumberWrong,
            userNumber = userNumber,
            onUserNumberChanged = onUserNumberChanged
        )
        DifficultySelector(
            difficultyOptions = difficultyOptions,
            selectedDifficulty = userNumber,
            onDifficultyChanged = onDifficultyChanged
        )
        StartGameButton(onStartGame = onStartGame)
    }
}