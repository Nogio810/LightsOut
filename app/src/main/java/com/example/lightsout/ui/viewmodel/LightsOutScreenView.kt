package com.example.lightsout.ui.viewmodel

import android.util.Log
import androidx.annotation.IntegerRes
import androidx.lifecycle.ViewModel
import com.example.lightsout.ui.game.flip
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class LightsOutScreenView : ViewModel() {
    private val _uiState = MutableStateFlow(LightsOutUiState())
    val uiState: StateFlow<LightsOutUiState> = _uiState.asStateFlow()

    private val _shouldStartGame = MutableStateFlow(false)
    val shouldStartGame: StateFlow<Boolean> = _shouldStartGame

    fun checkUserNumber(enterNumber: String, minMass: Int){
        val number = enterNumber.toIntOrNull()

        _uiState.update { it.copy(inputString = enterNumber) }

        Log.d("LightsOutGame", "checkUserNumber called with number: $number")

        if(number != null && number > 2 && number <= minMass){
            _uiState.update { currentState ->
                Log.d("LightsOutGame", "Updating rowNum to: $number")
                currentState.copy(rowNum = number, isNumberWrong = false)
            }
        }else{
            _uiState.update { currentState ->
                currentState.copy(isNumberWrong = true)
            }
        }
    }

    fun resetHomeScreenStates(){
        _uiState.update {
            it.copy(
                isShowingHomepage = true,
                alreadyGenerated = false,
                clickTimes = 0,
                indent = mutableListOf(),
                isShowAnswer = false,
                answerIndent = mutableListOf(),
                isShowHints = false,
                isShowClear = false,
                backCard = 0,
                rowNum = 0,
                inputString = ""
            )
        }
    }

    fun setDifficulties(desireDifficulty: String){
        _uiState.update { currentState ->
            currentState.copy(difficulty = desireDifficulty)
        }
    }

    fun updateClickTimes(){
        _uiState.update { currentState ->
            currentState.copy(
                clickTimes = currentState.clickTimes + 1
            )
        }
    }

    fun restartGame(){
        _uiState.update { currentState ->
            val newAnswerIndent = currentState.indent.toList()
            currentState.copy(
                answerIndent = newAnswerIndent,
                restartTime = currentState.restartTime + 1,
                restartBool = true,
                clickTimes = 0,
                backCard = 0
            )
        }
    }

    fun alreadyQuestionGenerated(){
        _uiState.update {
            it.copy(
                alreadyGenerated = true
            )
        }
    }

    fun answerShow(){
        _uiState.update {
            it.copy(
                isShowAnswer = true
            )
        }
    }

    fun checkCorrect(){
        _uiState.update {
            it.copy(
                isShowClear = true
            )
        }
    }

    fun showHints(){
        _uiState.update {
            it.copy(
                isShowHints = true
            )
        }
    }

    fun increaseBuckCard(){
        _uiState.update {
            it.copy(
                backCard = it.backCard + 1
            )
        }
    }

    fun decreaseBuckCard(){
        _uiState.update {
            it.copy(
                backCard = it.backCard - 1
            )
        }
    }

    fun restarted(){
        _uiState.update {
            it.copy(
                restartBool = false
            )
        }
    }

    fun triggerGameStart() {
        _shouldStartGame.value = true
    }

    fun resetGameStartFlag() {
        _shouldStartGame.value = false
    }

    fun onCellClicked(row: Int, col: Int, massNumber: Int){
        // 1. 状態を更新
        _uiState.update { currentState ->
            val oldGrid = currentState.currentGrid
            val newGrid  = oldGrid.copyOf() // 新しい配列を作成

            // 2. 盤面をトグル
            flip(newGrid, row, col, massNumber)
            flip(newGrid, row + 1, col, massNumber)
            flip(newGrid, row - 1, col, massNumber)
            flip(newGrid, row, col + 1, massNumber)
            flip(newGrid, row, col - 1, massNumber)

            // 3. answerIndent の更新
            val clickMassIndentNumber = row * massNumber + col
            val newAnswerIndent = currentState.answerIndent.toMutableList()
            if (newAnswerIndent.contains(clickMassIndentNumber)) {
                newAnswerIndent.remove(clickMassIndentNumber)
            } else {
                newAnswerIndent.add(clickMassIndentNumber)
            }

            // clickTimesの更新
            val newClickTimes = currentState.clickTimes + 1

            val newBackCardCount = newGrid.sumOf { Integer.bitCount(it) }

            // 4. 状態をコピーして返す (ここで再コンポーズがトリガーされる)
            currentState.copy(
                currentGrid = newGrid, // 新しい配列で更新
                answerIndent = newAnswerIndent.toList(), // 新しいリストで更新
                clickTimes = newClickTimes,
                backCard = newBackCardCount
            )
        }
    }

    fun setBackCardCount(count: Int) {
        _uiState.update {
            it.copy(backCard = count)
        }
    }

    fun setQuestionStates(newIndex: List<Int>, newAnswerIndex: List<Int>, newGrid: IntArray) {
        _uiState.update { currentState ->
            currentState.copy(
                indent = newIndex,
                answerIndent = newAnswerIndex,
                currentGrid = newGrid.copyOf() // 状態に保存
            )
        }
    }
}