package com.example.lightsout.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.lightsout.ui.components.DifficultySelector
import com.example.lightsout.ui.components.GameImage
import com.example.lightsout.ui.components.MassInputField
import com.example.lightsout.ui.components.StartGameButton

@Composable
fun MassNumberSetting(
    isNumberWrong: Boolean,
    onRowNumChanged: (String) ->Unit,
    rowNum: String,
    minMass: Int,
    difficultyOptions: List<String>,
    selectedDifficulty: String,
    onDifficultyChanged: (String) -> Unit,
    onStartGame: () -> Unit
){
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        GameImage()
        MassInputField(
            minMass = minMass,
            isNumberWrong = isNumberWrong,
            rowNum = rowNum,
            onRowNumChanged = onRowNumChanged
        )
        DifficultySelector(
            difficultyOptions = difficultyOptions,
            selectedDifficulty = selectedDifficulty,
            onDifficultyChanged = onDifficultyChanged
        )
        StartGameButton(onStartGame = onStartGame)
    }
}