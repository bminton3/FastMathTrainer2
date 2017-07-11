package com.minton.fastmathtrainer.MathCards

import android.content.Intent
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.SystemClock
import android.widget.TextView
import android.widget.Button
import android.widget.Chronometer
import com.minton.fastmathtrainer.R
import com.minton.fastmathtrainer.WinningScreenActivity
import kotlinx.android.synthetic.main.activity_math_cards.*
import java.security.SecureRandom


/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
abstract class MathCardsActivity : AppCompatActivity() {

    protected val mHideHandler = Handler()
    protected var total: Int = 0
    protected var scoreNumber: Int = 0

    /**
     * Maybe this is the main method
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_math_cards)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Set up the user interaction to manually show or hide the system UI.
        //equation.setOnClickListener { toggle() }

        val answer: TextView = findViewById<TextView>(R.id.answer)
        val chronometer: Chronometer = findViewById<Chronometer>(R.id.chronometer)

        chronometer.setBase(SystemClock.elapsedRealtime());
        chronometer.start()

        createButtonListeners()
    }
    /**
     * The stuff that runs right after the main method?
     */
    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)

        updateEquation()

        val answer: TextView = findViewById<TextView>(R.id.answer)

        score.text = "Score : " + scoreNumber
        answer.text = ""

        // Trigger the initial hide() shortly after the activity has been
        // created, to briefly hint to the user that UI controls
        // are available.
        //delayedHide(100)
    }

    protected fun randomInt(): Int {
        val random = SecureRandom()
        return random.nextInt(9)
    }

    protected fun createButtonListeners() {
        val oneButton : Button = findViewById<Button>(R.id.one)
        oneButton.setOnClickListener {
            answer.append(oneButton.text)
            checkAnswer()
        }
        val twoButton : Button = findViewById<Button>(R.id.two)
        twoButton.setOnClickListener {
            answer.append(twoButton.text)
            checkAnswer()
        }
        val threeButton : Button = findViewById<Button>(R.id.three)
        threeButton.setOnClickListener {
            answer.append(threeButton.text)
            checkAnswer()
        }
        val fourButton : Button = findViewById<Button>(R.id.four)
        fourButton.setOnClickListener {
            answer.append(fourButton.text)
            checkAnswer()
        }
        val fiveButton : Button = findViewById<Button>(R.id.five)
        fiveButton.setOnClickListener {
            answer.append(fiveButton.text)
            checkAnswer()
        }
        val sixButton : Button = findViewById<Button>(R.id.six)
        sixButton.setOnClickListener {
            answer.append(sixButton.text)
            checkAnswer()
        }
        val sevenButton : Button = findViewById<Button>(R.id.seven)
        sevenButton.setOnClickListener {
            answer.append(sevenButton.text)
            checkAnswer()
        }
        val eightButton : Button = findViewById<Button>(R.id.eight)
        eightButton.setOnClickListener {
            answer.append(eightButton.text)
            checkAnswer()
        }
        val nineButton : Button = findViewById<Button>(R.id.nine)
        nineButton.setOnClickListener {
            answer.append(nineButton.text)
            checkAnswer()
        }
        val zeroButton : Button = findViewById<Button>(R.id.zero)
        zeroButton.setOnClickListener {
            answer.append(zeroButton.text)
            checkAnswer()
        }
        val backButton : Button = findViewById<Button>(R.id.back)
        backButton.setOnClickListener {
            if (answer.text.length >= 1) {
                answer.text = answer.text.substring(0, answer.text.length - 1)
            }
        }
        val clearButton : Button = findViewById<Button>(R.id.clear)
        clearButton.setOnClickListener { answer.text = "" }
    }

    protected fun checkAnswer() {
        var oldColor : Int = answer.currentTextColor
        if (answer.text.toString().length == total.toString().length) {
            if (answer.text.toString().toInt() == total) {
                answer.setTextColor(Color.GREEN)
                updateEquation()
                clearTextSetColor(oldColor)
                scoreNumber++
                updateScore(scoreNumber)
            }
            else {
                answer.setTextColor(Color.RED)
                clearTextSetColor(oldColor)
            }
        }
    }

    protected fun updateScore(scoreNumber : Int) {
        score.text = "Score : " + scoreNumber
        if (scoreNumber == 10) {
            chronometer.stop()
            val elapsedMillis = SystemClock.elapsedRealtime() - chronometer.getBase()
            val intent = Intent(this, WinningScreenActivity::class.java)
            intent.putExtra("MESSAGE", "You won!")
            intent.putExtra("TIME", "Your time was " + elapsedMillis / 1000 + " seconds")
            this.startActivityForResult(intent, 0)
        }
    }

    protected fun clearTextSetColor(color: Int) {
        Handler().postDelayed( {
            answer.setTextColor(color)
            answer.text = ""
        }, 300);
    }


    open fun updateEquation() {}
}
