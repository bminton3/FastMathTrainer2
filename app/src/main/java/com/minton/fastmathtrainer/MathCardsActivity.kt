package com.minton.fastmathtrainer

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.TextView
import android.widget.Button
import kotlinx.android.synthetic.main.activity_math_cards.*


/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class MathCardsActivity : AppCompatActivity() {

    private var mVisible: Boolean = false
    private val mHideHandler = Handler()

    /**
     * Maybe this is the main method
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_math_cards)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        mVisible = true

        // Set up the user interaction to manually show or hide the system UI.
        //equation.setOnClickListener { toggle() }

        val answer: TextView = findViewById<TextView>(R.id.answer)

        createButtonListeners()
    }
    /**
     * The stuff that runs right after the main method?
     */
    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)

        val text: TextView = findViewById<TextView>(R.id.equation)

        text.text = "1+2"

        val answer: TextView = findViewById<TextView>(R.id.answer)

        answer.text = ""

        // Trigger the initial hide() shortly after the activity has been
        // created, to briefly hint to the user that UI controls
        // are available.
        //delayedHide(100)
    }

    private fun createButtonListeners() {
        val oneButton : Button = findViewById<Button>(R.id.one)
        oneButton.setOnClickListener { answer.text = oneButton.text }
        val twoButton : Button = findViewById<Button>(R.id.two)
        twoButton.setOnClickListener { answer.text = twoButton.text }
        val threeButton : Button = findViewById<Button>(R.id.three)
        threeButton.setOnClickListener { answer.text = threeButton.text }
        val fourButton : Button = findViewById<Button>(R.id.four)
        fourButton.setOnClickListener { answer.text = fourButton.text }
        val fiveButton : Button = findViewById<Button>(R.id.five)
        fiveButton.setOnClickListener { answer.text = fiveButton.text }
        val sixButton : Button = findViewById<Button>(R.id.six)
        sixButton.setOnClickListener { answer.text = sixButton.text }
        val sevenButton : Button = findViewById<Button>(R.id.seven)
        sevenButton.setOnClickListener { answer.text = sevenButton.text }
        val eightButton : Button = findViewById<Button>(R.id.eight)
        eightButton.setOnClickListener { answer.text = eightButton.text }
        val nineButton : Button = findViewById<Button>(R.id.nine)
        nineButton.setOnClickListener { answer.text = nineButton.text }
        val zeroButton : Button = findViewById<Button>(R.id.zero)
        zeroButton.setOnClickListener { answer.text = zeroButton.text }
    }

    companion object {
        /**
         * Whether or not the system UI should be auto-hidden after
         * [AUTO_HIDE_DELAY_MILLIS] milliseconds.
         */
        private val AUTO_HIDE = true

        /**
         * If [AUTO_HIDE] is set, the number of milliseconds to wait after
         * user interaction before hiding the system UI.
         */
        private val AUTO_HIDE_DELAY_MILLIS = 3000

        /**
         * Some older devices needs a small delay between UI widget updates
         * and a change of the status and navigation bar.
         */
        private val UI_ANIMATION_DELAY = 300
    }
}
