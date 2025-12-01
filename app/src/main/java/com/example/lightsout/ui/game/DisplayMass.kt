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
    gridState: IntArray,
    massSize: Int,
    update: () -> Unit,
    restart: Int,
    answerList: MutableList<Int>,
    answer: Boolean,
    increaseBackCard: () -> Unit,
    decreaseBackCard: () -> Unit,
    onCellClick: (row: Int, col: Int) -> Unit
){
    LazyVerticalGrid(
        columns = GridCells.Fixed(massNumber),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        contentPadding = PaddingValues(0.dp),
        verticalArrangement = Arrangement.spacedBy(0.dp),
        horizontalArrangement = Arrangement.spacedBy(0.dp)
    ) {
        items(
            massNumber * massNumber,
            key = { index -> index }
        ){index ->
            val row = index / massNumber
            val col = index % massNumber
            val isOn = (gridState[row] shr col) and 1 == 1

            LightCell(
                isOn = isOn,
                massSize = massSize,
                isAnswer = answer && index in answerList,
                onClick = {
                    val startTime = System.currentTimeMillis()
                    onCellClick(row, col)
                    update()
                    val endTime = System.currentTimeMillis()
                    Log.d("LightsOutGame", "クリック処理時間: ${endTime - startTime}ms")
                }
            )
        }
    }
}