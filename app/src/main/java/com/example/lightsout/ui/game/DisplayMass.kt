package com.example.lightsout.ui.game

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DisplayMass(
    massNumber: Int,
    initialGrid: IntArray,
    massSize: Int,
    update: () -> Unit,
    restart: Int,
    answerList: MutableList<Int>,
    answer: Boolean,
    increaseBackCard: () -> Unit,
    decreaseBackCard: () -> Unit
){
    var grid by remember(restart){
        mutableStateOf(initialGrid.copyOf())
    }

    LazyVerticalGrid(
        columns = GridCells.Fixed(massNumber),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        contentPadding = PaddingValues(0.dp),
        verticalArrangement = Arrangement.spacedBy(0.dp),
        horizontalArrangement = Arrangement.spacedBy(0.dp)
    ) {
        items(massNumber * massNumber){index ->
            val row = index / massNumber
            val col = index % massNumber
            val isOn = (grid[row] shr col) and 1 == 1

            LightCell(
                isOn = isOn,
                massSize = massSize,
                isAnswer = answer && index in answerList,
                onClick = {
                    val startTime = System.currentTimeMillis()
                    grid = toggleCell(grid, row, col, massNumber, answerList, increaseBackCard, decreaseBackCard)
                    update()
                    val endTime = System.currentTimeMillis()
                    Log.d("LightsOutGame", "クリック処理時間: ${endTime - startTime}ms")
                }
            )
        }
    }
}