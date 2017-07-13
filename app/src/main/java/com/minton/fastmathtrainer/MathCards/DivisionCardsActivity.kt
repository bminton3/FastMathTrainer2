package com.minton.fastmathtrainer.MathCards

import android.widget.TextView
import com.minton.fastmathtrainer.R

/**
 * Created by Ben on 7/11/2017.
 */
class DivisionCardsActivity : MathCardsActivity() {


    /** TODO figure out division */
    override fun updateEquation() {
        val text: TextView = findViewById<TextView>(R.id.equation)

        val x = randomInt()
        var y = randomInt()
        // avoid divide by zero
        if (y == 0) {
            y++
        }
        total = (x*y)/y
        text.text = (x*y).toString() + " / " + y.toString()
    }

}