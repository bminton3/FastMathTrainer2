package com.minton.fastmathtrainer.MathCards

import android.widget.TextView
import com.minton.fastmathtrainer.R

/**
 * Created by Ben on 7/11/2017.
 */
class SubtractionCardsActivity : MathCardsActivity() {

    override fun updateEquation() {
        val text: TextView = findViewById<TextView>(R.id.equation)

        var x = randomInt()
        var y = randomInt()
        if (y > x) {
            var temp = x
            x = y
            y = temp
        }
        total = x-y
        text.text = x.toString() + "-" + y.toString()
    }
}