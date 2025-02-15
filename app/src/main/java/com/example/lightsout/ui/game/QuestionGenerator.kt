package com.example.lightsout.ui.game

import android.util.Log

fun questionGeneration(
    difficulty: String,
    massNumber: Int,
    index: MutableList<Int>,
    generated: Boolean,
    questionGenerated: () -> Unit,
    answerIndex: MutableList<Int>,
    increaseBackCard: () -> Unit,
    decreaseBackCard: () -> Unit,
    restarted: () -> Unit
): IntArray {
    Log.d("LightsOutGame", "questionGeneration called")

    val grid = IntArray(massNumber) { 0 }

    if (!generated) {
        val mass: Int = when (difficulty) {
            "Hard" -> (massNumber * massNumber * 0.7).toInt()
            "Normal" -> (massNumber * massNumber * 0.5).toInt()
            else -> (massNumber * massNumber * 0.3).toInt()
        }

        val newIndex = (0 until massNumber * massNumber).shuffled().take(mass)

        index.clear()
        index.addAll(newIndex)

        answerIndex.clear()
        answerIndex.addAll(index)

        questionGenerated()
        Log.d("LightsOutGame", "Generate Question: $index")
    }

    for (i in index) {
        val row = i / massNumber
        val col = i % massNumber
        flip(grid, row, col, massNumber, increaseBackCard, decreaseBackCard)
        flip(grid, row + 1, col, massNumber, increaseBackCard, decreaseBackCard)
        flip(grid, row - 1, col, massNumber, increaseBackCard, decreaseBackCard)
        flip(grid, row, col + 1, massNumber, increaseBackCard, decreaseBackCard)
        flip(grid, row, col - 1, massNumber, increaseBackCard, decreaseBackCard)
    }

    restarted()
    return grid
}