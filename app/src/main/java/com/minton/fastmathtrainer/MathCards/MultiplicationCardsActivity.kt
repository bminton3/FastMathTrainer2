package com.minton.fastmathtrainer.MathCards

import android.widget.TextView
import com.minton.fastmathtrainer.R

/**
 * Created by Ben on 7/11/2017.
 */
class MultiplicationCardsActivity : MathCardsActivity() {

    override fun updateEquation() {
        val text: TextView = findViewById<TextView>(R.id.equation)

        val x = randomInt()
        val y = randomInt()
        total = x*y
        text.text = x.toString() + " x " + y.toString()
    }
}