package com.minton.fastmathtrainer.MathCards

import android.widget.TextView
import com.minton.fastmathtrainer.R

/**
 * Created by Ben on 7/11/2017.
 */
class MultiplicationCardsActivity : MathCardsActivity() {

    private val firstNumber : Number = Number()
    private val secondNumber : Number = Number()

    override fun updateEquation() {
        val text: TextView = findViewById<TextView>(R.id.equation)


        val x = firstNumber.randomInt("multiplication", pref)
        val y = secondNumber.randomInt("multiplication", pref)
        total = x*y
        text.text = x.toString() + " x " + y.toString()
    }
}