package com.minton.fastmathtrainer.MathCards

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.minton.fastmathtrainer.R

class AdditionCardsActivity : MathCardsActivity() {

    val firstNumber : Number = Number()
    val secondNumber : Number = Number()

    override fun updateEquation() {
        val text: TextView = findViewById<TextView>(R.id.equation)

        val x = firstNumber.randomInt("addition", pref)
        val y = secondNumber.randomInt("addition", pref)
        total = x+y
        text.text = x.toString() + " + " + y.toString()
    }
}
