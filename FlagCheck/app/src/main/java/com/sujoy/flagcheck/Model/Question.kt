package com.sujoy.flagcheck.Model

data class Question(
    val text: String,
    val isGreenAnswerYes: Boolean // true = Yes is green flag, false = No is green flag
)
