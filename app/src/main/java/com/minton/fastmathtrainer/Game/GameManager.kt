//package com.minton.fastmathtrainer.Game
//
//import android.content.Intent
//import android.content.SharedPreferences
//import android.graphics.Color
//import android.os.CountDownTimer
//import android.os.SystemClock
//import android.widget.Chronometer
//import android.widget.TextView
//import com.minton.fastmathtrainer.Generic.WinningScreenActivity
//import com.minton.fastmathtrainer.Generic.gameDuration
//import com.minton.fastmathtrainer.Generic.gameMode
//import com.minton.fastmathtrainer.MathCards.MathCardsActivity
//import com.minton.fastmathtrainer.R
//import kotlinx.android.synthetic.main.activity_math_cards.*
//import android.R
//
//
//
//class GameManager : GameManagerIfc {
//
//    protected var total: Int = 0
//    protected var answeredCorrect: Int = 0
//    protected var totalAnswered: Int = 0
//    protected var currentMathCard: MathCardsActivity? = null
//
//    /**
//     * When a number is entered, we come here to see if the length of the answer is equal to the
//     * length of the actual answer. If it is, we go ahead and check it. To create a color blink
//     * effect, we store the old color value of the user's answer, show the right/wrong answer color,
//     * then reset the old color.
//     * TODO add sounds
//     */
//    override fun checkAnswer(answerView : TextView, pref : SharedPreferences, mathCard : MathCardsActivity) {
//        var oldColor : Int = answerView.currentTextColor
//        if (answerView.text.toString().length == total.toString().length) {
//            totalAnswered++;
//            if (answerView.text.toString().toInt() == total) {
//                answerView.setTextColor(Color.GREEN)
//                //winningBing.start() TODO update bing noise
//                mathCard.updateEquation()
//                mathCard.clearTextSetColor(oldColor)
//                answeredCorrect++
//                updateScore(answeredCorrect, pref)
//            }
//            else {
//                answerView.setTextColor(Color.RED)
//                mathCard.clearTextSetColor(oldColor)
//            }
//        }
//    }
//
//    override fun updateScore(answeredCorrect : Int, pref : SharedPreferences) {
//        try {
//            val gameMode = pref.getString(gameMode, "practice")
//            val duration = pref.getInt(gameDuration, 15)
//
//            score.text = "Score : " + answeredCorrect
//            if (answeredCorrect == 15 && gameMode.equals("timed")) {
//                chronometer.stop()
//                val elapsedMillis = SystemClock.elapsedRealtime() - chronometer.getBase()
//                val intent = Intent(this, WinningScreenActivity::class.java)
//                val score : Double = (answeredCorrect.toDouble()/totalAnswered.toDouble()) * 100
//                intent.putExtra("MESSAGE",  "" + elapsedMillis / 1000 + " seconds")
//                intent.putExtra("TIME", "" + "%.2f".format(score)  + "% correct")
//                this.answeredCorrect = 0;
//                this.totalAnswered = 0;
//                this.startActivityForResult(intent, 0)
//            }
//        }
//        catch (e : UninitializedPropertyAccessException) {
//            // idk
//        }
//    }
//
//    override fun startGame(chronometer: Chronometer) {
//        chronometer.setBase(SystemClock.elapsedRealtime());
//        chronometer.start()
//    }
//
//    private fun startTimer(){
//        object : CountDownTimer(30000, 1000) {
//
//            override fun onTick(millisUntilFinished: Long) {
//                mTextField.setText("seconds remaining: " + millisUntilFinished / 1000)
//            }
//
//            override fun onFinish() {
//                mTextField.setText("done!")
//            }
//        }.start()
//    }
//}