package com.example.lightsout.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class LightsOutScreenView : ViewModel() {
    var userNumber by mutableStateOf("")
        private set

    private val _uiState = MutableStateFlow(LightsOutUiState())
    val uiState: StateFlow<LightsOutUiState> = _uiState.asStateFlow()

    private var getNumber = false
    private var getDifficulty = false

    fun updateUserNumber(enterNumber: String) {
        userNumber = enterNumber
    }

    fun checkUserNumber(minMass: Int){
        if((userNumber.toInt() <= minMass) and (true) and (userNumber.toInt() > 1)){
            _uiState.update { currentState ->
                currentState.copy(rowNum = userNumber.toInt())
            }
            getNumber = true
        }else{
            _uiState.update { currentState ->
                currentState.copy(isNumberWrong = true)
            }
        }

        updateUserNumber("")
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
                backCard = 0
            )
        }
        getNumber = false
        getDifficulty = false
    }

    fun setDifficulties(desireDifficulty: String){
        _uiState.update { currentState ->
            currentState.copy(difficulty = desireDifficulty)
        }
        getDifficulty = true
    }

    fun updateGameScreen(){
        if (getNumber and getDifficulty){
            _uiState.update {
                it.copy(
                    isShowingHomepage = false
                )
            }
        }else{
            getNumber = false
            getDifficulty = false
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
                restart = currentState.restart + 1,
                clickTimes = 0,
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

    fun playGuideShow(){
        _uiState.update {
            it.copy(
                isShowPlayGuide = true
            )
        }
    }

    fun playGuideHide(){
        _uiState.update {
            it.copy(
                isShowPlayGuide = false
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
}