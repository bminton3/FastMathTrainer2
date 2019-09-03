package com.minton.fastmathtrainer.MathCards

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.minton.fastmathtrainer.R

class AdditionCardsActivity : MathCardsActivity() {

    private val firstNumber : Number = Number()
    private val secondNumber : Number = Number()
    override var gameType : String = "addition"

    override fun updateEquation() {
        val text: TextView = findViewById<TextView>(R.id.equation)

        val x = firstNumber.randomInt(gameType, pref)
        val y = secondNumber.randomInt(gameType, pref)
        total = x+y
        text.text = x.toString() + " + " + y.toString()
    }
}
