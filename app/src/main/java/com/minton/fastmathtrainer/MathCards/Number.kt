package com.minton.fastmathtrainer.MathCards

import android.content.SharedPreferences
import android.util.Log
import java.security.SecureRandom

/**
 * Sets itself based on difficulty
 * Created by Ben on 2/8/2018.
 */
class Number {
    private var lastRandomNumber : Int = 0

    protected fun generateFreshRandom(bound : Int) : Int {
        val random = SecureRandom()
        var nextRandomInt = random.nextInt(bound)
        // false for left, true for right
                while (nextRandomInt == lastRandomNumber) {
                    nextRandomInt = random.nextInt(bound)
                }
                //Log.i("Number.generateFreshRandom()", "last random int:" + lastRandomNumber + " and next random int:" + nextRandomInt)
                lastRandomNumber = nextRandomInt

        return nextRandomInt
    }

    public fun randomInt(type: String, pref : SharedPreferences): Int {
        val random = SecureRandom()
        val difficulty = pref.getString("difficulty", "easy")
        //Log.i("Number.randomInt()", "difficulty setting:" + difficulty + " prefs contains difficulty:" + pref.contains("difficulty"))
        when (difficulty) {
            "easy" -> {
                when (type) {
                    "addition" -> return generateFreshRandom(9)
                    "subtraction" -> return generateFreshRandom(9)
                    "multiplication" -> return generateFreshRandom(9)
                    "division" -> return generateFreshRandom(9)
                    else -> return generateFreshRandom(9)
                }

            }
            "medium" -> {
                when (type) {
                    "addition" -> return generateFreshRandom(19)
                    "subtraction" -> return generateFreshRandom(19)
                    "multiplication" -> return generateFreshRandom(12)
                    "division" -> return generateFreshRandom(12)
                    else -> return generateFreshRandom(12)
                }
            }
            "hard" -> {
                when (type) {
                    "addition" -> return generateFreshRandom(50)
                    "subtraction" -> return generateFreshRandom(50)
                    "multiplication" -> return generateFreshRandom(19)
                    "division" -> return generateFreshRandom(19)
                    else -> return generateFreshRandom(19)
                }
            }
            else -> return generateFreshRandom(9)
        }
    }
}