package com.minton.fastmathtrainer.MathCards

import android.util.Log
import android.widget.TextView
import com.minton.fastmathtrainer.R
import java.security.SecureRandom

/**
 * Created by Ben on 7/11/2017.
 */
class DivisionCardsActivity : MathCardsActivity() {

    private val firstNumber : Number = Number()
    private val secondNumber : Number = Number()
    override var gameType : String = "division"

    /** TODO figure out division */
    override fun updateEquation() {
        val text: TextView = findViewById<TextView>(R.id.equation)

        val x = firstNumber.randomInt(gameType, pref)
        var y = secondNumber.randomInt(gameType, pref)
        // avoid divide by zero
        if (y == 0) {
            y++
        }
        total = (x*y)/y
        text.text = (x*y).toString() + " / " + y.toString()
    }

}