package com.minton.fastmathtrainer.MathCards

import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.TextView
import android.widget.Button
import com.minton.fastmathtrainer.Generic.BaseActivity
import com.minton.fastmathtrainer.R
import com.minton.fastmathtrainer.Generic.WinningScreenActivity
import kotlinx.android.synthetic.main.activity_math_cards.*
import android.media.MediaPlayer
import android.os.CountDownTimer
import android.util.Log
import android.widget.LinearLayout
import com.minton.fastmathtrainer.Generic.gameDuration
import com.minton.fastmathtrainer.Generic.gameMode
import com.minton.fastmathtrainer.Menus.MainMenuActivity
import com.minton.fastmathtrainer.Style.StyleHandler
import kotlin.math.roundToInt

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
abstract class MathCardsActivity : BaseActivity() {

    protected val mHideHandler = Handler()
    protected var total: Int = 0
    protected var answeredCorrect: Int = 0
    protected var totalAnswered: Int = 0
    protected lateinit var pref : SharedPreferences
    protected lateinit var winningBing : MediaPlayer
    private lateinit var countdownTimer: CountDownTimer

    /**
     * Maybe this is the main method
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_math_cards)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Set up the user interaction to manually show or hide the system UI.
        //equation.setOnClickListener { toggle() }

        pref = getSharedPreferences("fastmathtrainer",0)
        val gameMode = pref.getString(gameMode, "practice")
        val linearLayout = findViewById(R.id.baseLayout) as LinearLayout

        if (gameMode.equals("timed")) {
            timer.text = pref.getInt(gameDuration, 15).toString()
            startTimer(pref.getInt(gameDuration, 15).toLong()*1000)
            linearLayout.setBackgroundResource(R.drawable.android_gradient_timed_play)
        }
        else {
            timer.visibility = View.INVISIBLE
            val score : TextView = findViewById<TextView>(R.id.score)
            score.visibility = View.INVISIBLE
            linearLayout.setBackgroundResource(R.drawable.android_gradient_list)
        }
        StyleHandler().runAnimatedBackground(findViewById(R.id.baseLayout), gameMode)
        createButtonListeners()
        //winningBing = MediaPlayer.create(applicationContext, R.raw.shatiabing)
    }

    /**
     * The stuff that runs right after the main method?
     */
    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)

        updateEquation()

        val answer: TextView = findViewById<TextView>(R.id.answer)

        score.text = "Score : " + answeredCorrect
        answer.text = ""

        // Trigger the initial hide() shortly after the activity has been
        // created, to briefly hint to the user that UI controls
        // are available.
        //delayedHide(100)
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
        clear.setOnClickListener { answer.text = "" }
    }

    /**
     * When a number is entered, we come here to see if the length of the answer is equal to the
     * length of the actual answer. If it is, we go ahead and check it. To create a color blink
     * effect, we store the old color value of the user's answer, show the right/wrong answer color,
     * then reset the old color.
     * TODO add sounds
     */
    protected fun checkAnswer() {
        var oldColor : Int = answer.currentTextColor
        if (answer.text.toString().length == total.toString().length) {
            totalAnswered++
            if (answer.text.toString().toInt() == total) {
                answer.setTextColor(Color.GREEN)
                //winningBing.start() TODO update bing noise
                updateEquation()
                clearTextSetColor(oldColor)
                answeredCorrect++
                score.text = "Score : " + answeredCorrect
            }
            else {
                answer.setTextColor(Color.RED)
                clearTextSetColor(oldColor)
            }
        }
    }

    fun finishGame() {
        val gameMode = pref.getString(gameMode, "practice")
        if (gameMode.equals("timed")) {
            val score : Double
            val intent = Intent(this, WinningScreenActivity::class.java)
            if (totalAnswered == 0) {
                score = 0.0
            }
            else {
                score = (answeredCorrect.toDouble()/totalAnswered.toDouble()) * 100
            }

            intent.putExtra("MESSAGE",  totalAnswered.toString() + " answered")
            intent.putExtra("TIME", "" + "%.2f".format(score)  + "% correct")
            intent.putExtra("TOTALSCORE", "Total Score:\n" + (totalAnswered*(score/100)).roundToInt())
            val activityCalledFrom = "com.minton.fastmathtrainer." + this.localClassName
            intent.putExtra("CALLINGACTIVITY", activityCalledFrom)
            this.answeredCorrect = 0
            this.totalAnswered = 0
            this.startActivityForResult(intent, 0)
        }
    }

    fun clearTextSetColor(color: Int) {
        Handler().postDelayed( {
            answer.setTextColor(color)
            answer.text = ""
        }, 300);
    }

    private fun startTimer(timeInMillis : Long){
        countdownTimer = object : CountDownTimer(timeInMillis, 1000) {

            override fun onTick(millisUntilFinished: Long) {
                timer.setText((millisUntilFinished / 1000).toString())
            }

            override fun onFinish() {
                finishGame()
            }
        }.start()
    }

    override fun onBackPressed() {
        if (::countdownTimer.isInitialized){
            countdownTimer.cancel()
        }
        val addIntent = Intent(this, MainMenuActivity::class.java)
        this.startActivity(addIntent)
    }

    /**
     * This is implemented by each math card type
     */
    open fun updateEquation() {}
}
