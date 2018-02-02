package com.minton.fastmathtrainer.MathCards

import android.util.Log
import android.widget.TextView
import com.minton.fastmathtrainer.R
import java.security.SecureRandom

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

    override fun randomInt(): Int {
        val random = SecureRandom()
        val difficulty = pref.getString("difficulty", "easy")
        Log.i("MathCardsActivity.randomInt()", "difficulty setting:" + difficulty + " prefs contains difficulty:" + pref.contains("difficulty"))
        when (difficulty) {
            "easy" -> return random.nextInt(5)
            "medium" -> return random.nextInt(7)
            "hard" -> return random.nextInt(9)
            else -> return random.nextInt(5)
        }
    }

}