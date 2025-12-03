package com.example.lightsout.ui.viewmodel

import androidx.compose.runtime.Immutable

@Immutable
data class LightsOutUiState(
    val isShowingHomepage: Boolean = true,
    val isNumberWrong: Boolean = false,
    val isShowAnswer: Boolean = false,
    val isShowPlayGuide: Boolean = false,
    val isShowClear: Boolean = false,
    val isShowHints: Boolean = false,
    val difficulty: String = "",
    val rowNum: Int = 0,
    val currentGrid: IntArray = intArrayOf(),
    val indent: List<Int> = emptyList(),
    val answerIndent: Set<Int> = emptySet(),
    val clickTimes: Int = 0,
    val restartTime: Int = 0,
    val restartBool: Boolean = false,
    val alreadyGenerated: Boolean = false,
    val backCard: Int = 0,
    val inputString: String = ""
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as LightsOutUiState

        if (isShowingHomepage != other.isShowingHomepage) return false
        if (isNumberWrong != other.isNumberWrong) return false
        if (isShowAnswer != other.isShowAnswer) return false
        if (isShowPlayGuide != other.isShowPlayGuide) return false
        if (isShowClear != other.isShowClear) return false
        if (isShowHints != other.isShowHints) return false
        if (difficulty != other.difficulty) return false
        if (rowNum != other.rowNum) return false
        if (!currentGrid.contentEquals(other.currentGrid)) return false
        if (indent != other.indent) return false
        if (answerIndent != other.answerIndent) return false
        if (clickTimes != other.clickTimes) return false
        if (restartTime != other.restartTime) return false
        if (restartBool != other.restartBool) return false
        if (alreadyGenerated != other.alreadyGenerated) return false
        if (backCard != other.backCard) return false
        if (inputString != other.inputString) return false

        return true
    }

    override fun hashCode(): Int {
        var result = isShowingHomepage.hashCode()
        result = 31 * result + isNumberWrong.hashCode()
        result = 31 * result + isShowAnswer.hashCode()
        result = 31 * result + isShowPlayGuide.hashCode()
        result = 31 * result + isShowClear.hashCode()
        result = 31 * result + isShowHints.hashCode()
        result = 31 * result + difficulty.hashCode()
        result = 31 * result + rowNum
        result = 31 * result + currentGrid.contentHashCode()
        result = 31 * result + indent.hashCode()
        result = 31 * result + answerIndent.hashCode()
        result = 31 * result + clickTimes
        result = 31 * result + restartTime
        result = 31 * result + restartBool.hashCode()
        result = 31 * result + alreadyGenerated.hashCode()
        result = 31 * result + backCard
        result = 31 * result + inputString.hashCode()
        return result
    }
}