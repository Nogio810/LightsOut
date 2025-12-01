package com.example.lightsout.ui.viewmodel

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
    val answerIndent: List<Int> = emptyList(),
    val clickTimes: Int = 0,
    val restartTime: Int = 0,
    val restartBool: Boolean = false,
    val alreadyGenerated: Boolean = false,
    val backCard: Int = 0,
    val inputString: String = ""
){
    // IntArrayは等価性チェックが難しいので、手動でequalsとhashCodeを実装するか、
    // 配列のコピーを防ぐためにこの方法を採用
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as LightsOutUiState
        // 他のプロパティの比較...
        if (!currentGrid.contentEquals(other.currentGrid)) return false // 配列の比較
        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + currentGrid.contentHashCode()
        return result
    }
}
