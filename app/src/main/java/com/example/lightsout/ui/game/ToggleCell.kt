package com.example.lightsout.ui.game

import android.util.Log

fun toggleCell(
    grid: IntArray,
    row: Int,
    col: Int,
    massNumber: Int,
    answerIndent: MutableList<Int>,
    increaseBackCard: () -> Unit,
    decreaseBackCard: () -> Unit
): IntArray{
    val startTime = System.currentTimeMillis()
    val newGrid  =grid.copyOf()

    flip(newGrid, row, col, massNumber, increaseBackCard, decreaseBackCard)
    flip(newGrid, row + 1, col, massNumber, increaseBackCard, decreaseBackCard)
    flip(newGrid, row - 1, col, massNumber, increaseBackCard, decreaseBackCard)
    flip(newGrid, row, col + 1, massNumber, increaseBackCard, decreaseBackCard)
    flip(newGrid, row, col - 1, massNumber, increaseBackCard, decreaseBackCard)

    val clickMassIndentNumber = row * massNumber + col
    if (answerIndent.contains(clickMassIndentNumber)) {
        answerIndent.remove(clickMassIndentNumber)
    } else {
        answerIndent.add(clickMassIndentNumber)
    }

    val endTime = System.currentTimeMillis()
    Log.d("LightsOutGame", "toggleCell 処理時間: ${endTime - startTime}ms")
    return newGrid
}