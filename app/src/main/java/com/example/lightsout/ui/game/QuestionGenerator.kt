package com.example.lightsout.ui.game

import android.util.Log

data class QuestionGeneratorResult(
    val grid: IntArray,
    val newIndex: List<Int>,
    val newAnswerIndex: List<Int>,
    val totalLights: Int
)

fun questionGeneration(
    difficulty: String,
    massNumber: Int,
    generated: Boolean,
    questionGenerated: () -> Unit,
    setBackCardCount: (Int) -> Unit,
    restarted: () -> Unit
): QuestionGeneratorResult {
    Log.d("LightsOutGame", "questionGeneration called")

    val grid = IntArray(massNumber) { 0 }

    var newIndex: List<Int> = emptyList()
    var newAnswerIndex: List<Int> = emptyList()

    if (!generated) {
        val mass: Int = when (difficulty) {
            "Hard" -> (massNumber * massNumber * 0.7).toInt()
            "Normal" -> (massNumber * massNumber * 0.5).toInt()
            else -> (massNumber * massNumber * 0.3).toInt()
        }

        val newGenerateIndex = (0 until massNumber * massNumber).shuffled().take(mass)

        newIndex = newGenerateIndex
        newAnswerIndex = newGenerateIndex

        questionGenerated()
        Log.d("LightsOutGame", "Generate Question: $newIndex")
    }

    for (i in newIndex) {
        val row = i / massNumber
        val col = i % massNumber
        flip(grid, row, col, massNumber)
        flip(grid, row + 1, col, massNumber)
        flip(grid, row - 1, col, massNumber)
        flip(grid, row, col + 1, massNumber)
        flip(grid, row, col - 1, massNumber)
    }
    val totalLights = grid.sumOf { Integer.bitCount(it) }
    setBackCardCount(totalLights)

    restarted()
    return QuestionGeneratorResult(
        grid = grid,
        newIndex = newIndex,
        newAnswerIndex = newAnswerIndex,
        totalLights = totalLights
    )
}