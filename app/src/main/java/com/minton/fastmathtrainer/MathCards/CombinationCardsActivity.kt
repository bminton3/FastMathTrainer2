package com.minton.fastmathtrainer.MathCards

import android.widget.TextView
import com.minton.fastmathtrainer.R

/**
 * Created by Ben on 7/12/2017.
 */
class CombinationCardsActivity : MathCardsActivity() {

    val operation = arrayOf("addition", "subtraction", "multiplication", "division")

    override fun updateEquation() {
        val text: TextView = findViewById<TextView>(R.id.equation)

        var x = randomInt()
        var y = randomInt()

        val operationSelection = randomInt() % 4
        val operationToShow = operation.get(operationSelection)

        if (operationToShow.equals("addition")) {
            total = x+y
            text.text = x.toString() + " + " + y.toString()
        }
        else if (operationToShow.equals("subtraction")) {
            if (y > x) {
                var temp = x
                x = y
                y = temp
            }
            total = x-y
            text.text = x.toString() + " - " + y.toString()
        }
        else if (operationToShow.equals("multiplication")) {
            total = x*y
            text.text = x.toString() + " x " + y.toString()
        }
        else {
            // avoid divide by zero
            if (y == 0) {
                y++
            }
            total = (x*y)/y
            text.text = (x*y).toString() + " / " + y.toString()
        }
    }
}