package com.minton.fastmathtrainer.Game

import android.content.SharedPreferences
import android.widget.Chronometer
import android.widget.TextView
import com.minton.fastmathtrainer.MathCards.MathCardsActivity

interface GameManagerIfc {
    fun checkAnswer(answerView : TextView, pref : SharedPreferences, mathCard : MathCardsActivity)
    fun updateScore(scoreNumber : Int, pref : SharedPreferences)
    fun startGame(chronometer: Chronometer)
}