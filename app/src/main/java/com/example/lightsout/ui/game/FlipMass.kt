package com.example.lightsout.ui.game

fun flip(
    grid: IntArray,
    r: Int,
    c: Int,
    massNumber: Int,
) {
    val mask = (1 shl massNumber) - 1
    if (r in 0 until massNumber && c in 0 until massNumber) {
        grid[r] = (grid[r] xor (1 shl c)) and mask
    }
}