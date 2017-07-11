package com.minton.fastmathtrainer.MathCards

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.minton.fastmathtrainer.R

class AdditionCardsActivity : MathCardsActivity() {

    override fun updateEquation() {
        val text: TextView = findViewById<TextView>(R.id.equation)

        val x = randomInt()
        val y = randomInt()
        total = x+y
        text.text = x.toString() + "+" + y.toString()
    }
}
