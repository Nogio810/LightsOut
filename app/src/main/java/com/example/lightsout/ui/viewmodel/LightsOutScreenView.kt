package com.example.lightsout.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
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
            currentState.answerIndent.clear()
            currentState.answerIndent.addAll(currentState.indent)
            currentState.copy(
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
}