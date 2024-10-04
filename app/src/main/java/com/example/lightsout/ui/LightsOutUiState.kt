package com.example.lightsout.ui

data class LightsOutUiState(
    val isShowingHomepage: Boolean = true,
    val isNumberWrong: Boolean = false,
    val isShowAnswer: Boolean = false,
    val isShowPlayGuide: Boolean = false,
    val isShowClear: Boolean = false,
    val isShowHints: Boolean = false,
    val difficulty: String = "",
    val rowNum: Int = 0,
    val indent: MutableList<Int> = mutableListOf(),
    val answerIndent: MutableList<Int> = mutableListOf(),
    val clickTimes: Int = 0,
    val restart: Int = 0,
    val alreadyGenerated: Boolean = false
)
