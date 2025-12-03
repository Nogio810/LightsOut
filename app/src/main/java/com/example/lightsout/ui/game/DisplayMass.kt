package com.example.lightsout.ui.game

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DisplayMass(
    massNumber: Int,
    gridState: IntArray,
    massSize: Int,
    answerList: Set<Int>,
    answer: Boolean,
    onCellClicked: (row: Int, col: Int, massNumber: Int) -> Unit
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
                modifier = Modifier.clickable{
                    onCellClicked(row, col, massNumber)
                }
            )
        }
    }
}