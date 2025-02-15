package com.example.lightsout.ui.game

fun flip(
    grid: IntArray,
    r: Int,
    c: Int,
    massNumber: Int,
    increaseBackCard: () -> Unit,
    decreaseBackCard: () -> Unit
) {
    val mask = (1 shl massNumber) - 1
    if (r in 0 until massNumber && c in 0 until massNumber) {
        val oldState = (grid[r] shr c) and 1
        grid[r] = (grid[r] xor (1 shl c)) and mask
        val newState = oldState xor 1
        if (newState == 1) {
            increaseBackCard()
        } else {
            decreaseBackCard()
        }
    }
}