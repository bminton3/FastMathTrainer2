package com.minton.fastmathtrainer.MathCards

import android.widget.TextView
import com.minton.fastmathtrainer.R
import java.security.SecureRandom

/**
 * Created by Ben on 7/12/2017.
 */
class CombinationCardsActivity : MathCardsActivity() {

    private val operation = arrayOf("addition", "subtraction", "multiplication", "division")
    private val firstNumber : Number = Number()
    private val secondNumber : Number = Number()

    override fun updateEquation() {
        val text: TextView = findViewById<TextView>(R.id.equation)

        val random = SecureRandom()
        val operationSelection = random.nextInt(4)
        val operationToShow = operation.get(operationSelection)

        if (operationToShow.equals("addition")) {
            val x = firstNumber.randomInt(operationToShow, pref)
            val y = secondNumber.randomInt(operationToShow, pref)
            total = x+y
            text.text = x.toString() + " + " + y.toString()
        }
        else if (operationToShow.equals("subtraction")) {
            var x = firstNumber.randomInt(operationToShow, pref)
            var y = secondNumber.randomInt(operationToShow, pref)
            if (y > x) {
                var temp = x
                x = y
                y = temp
            }
            total = x-y
            text.text = x.toString() + " - " + y.toString()
        }
        else if (operationToShow.equals("multiplication")) {
            val x = firstNumber.randomInt(operationToShow, pref)
            val y = secondNumber.randomInt(operationToShow, pref)
            total = x*y
            text.text = x.toString() + " x " + y.toString()
        }
        else {
            val x = firstNumber.randomInt(operationToShow, pref)
            var y = secondNumber.randomInt(operationToShow, pref)
            // avoid divide by zero
            if (y == 0) {
                y++
            }
            total = (x*y)/y
            text.text = (x*y).toString() + " / " + y.toString()
        }
    }
}