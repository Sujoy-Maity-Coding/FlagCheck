package com.sujoy.flagcheck.ViewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.sujoy.flagcheck.Model.Question
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class QuizViewModel @Inject constructor(): ViewModel() {

    private val _questions = listOf(
        Question("Do they support your goals?", true),
        Question("Do they get angry over small things?", false),
        Question("Do they communicate well?", true),
        Question("Do they invade your privacy?", false),
        Question("Do they respect your boundaries?", true)
    )

    private val _currentQuestionIndex = mutableStateOf(0)
    val currentQuestionIndex: State<Int> = _currentQuestionIndex

    val currentQuestion: Question
        get() = _questions[_currentQuestionIndex.value]

    private val _greenScore = mutableStateOf(0)
    private val _redScore = mutableStateOf(0)

    val greenScore: State<Int> = _greenScore
    val redScore: State<Int> = _redScore

    private val _isGameFinished = mutableStateOf(false)
    val isGameFinished: State<Boolean> = _isGameFinished

    fun answer(isYes: Boolean) {
        val question = currentQuestion
        if ((isYes && question.isGreenAnswerYes) || (!isYes && !question.isGreenAnswerYes)) {
            _greenScore.value++
        } else {
            _redScore.value++
        }

        if (_currentQuestionIndex.value < _questions.lastIndex) {
            _currentQuestionIndex.value++
        } else {
            _isGameFinished.value = true
        }
    }

    fun resetGame() {
        _currentQuestionIndex.value = 0
        _greenScore.value = 0
        _redScore.value = 0
        _isGameFinished.value = false
    }
}
