package com.minton.fastmathtrainer.Menus

import android.content.SharedPreferences
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.util.Log
import android.widget.*
import com.minton.fastmathtrainer.Generic.BaseActivity
import com.minton.fastmathtrainer.R
import com.minton.fastmathtrainer.Style.StyleHandler

/**
 *  TODO add game length setting and prompt user that's how many they need to answer.
 *  TODO store user speed in a local DB and keep track of user progress
 *  TODO have users sign in to compare their progress
 */
class SettingsActivity : BaseActivity() {

    protected lateinit var pref : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pref = getSharedPreferences("fastmathtrainer",0)

        val gameMode = pref.getString("gameMode", "practice")
        val difficulty = pref.getString("difficulty", "easy")

        setContentView(R.layout.activity_settings)
        setSavedDifficulty(difficulty)
        setGameMode(gameMode)
        createSwitchListener();
        val relativeLayout = findViewById(R.id.baseLayout) as RelativeLayout
        if (!gameMode.equals("timed")) {

            relativeLayout.setBackgroundResource(R.drawable.android_gradient_list)
        }
        else{
            relativeLayout.setBackgroundResource(R.drawable.android_gradient_timed_play)
        }
        StyleHandler().runAnimatedBackground(findViewById(R.id.baseLayout), gameMode)
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
        val difficultySetting : RadioGroup = findViewById<RadioGroup>(R.id.radioButtonGroup)
        val difficultySelected = difficultySetting.checkedRadioButtonId
        var pref = getSharedPreferences("fastmathtrainer",0).edit()
        when (difficultySelected) {
            R.id.easyRadioButton -> pref.putString("difficulty", "easy")
            R.id.mediumRadioButton -> pref.putString("difficulty", "medium")
            R.id.hardRadioButton -> pref.putString("difficulty", "hard")
        }

        pref.commit()
        Log.i("onPause() called.", " Saving state to " + pref)
    }

    private fun setGameMode(gameMode : String) {
        var gameModeSetting: Switch = findViewById<Switch>(R.id.gameModeSwitch)
        gameModeSetting.isChecked = gameMode.equals("timed")
    }

    private fun setSavedDifficulty(difficulty : String) {
        var defaultSetting : RadioButton? = null
        when (difficulty) {
            "easy" -> defaultSetting = findViewById<RadioButton>(R.id.easyRadioButton)
            "medium" -> defaultSetting = findViewById<RadioButton>(R.id.mediumRadioButton)
            "hard" -> defaultSetting = findViewById<RadioButton>(R.id.hardRadioButton)
            else -> defaultSetting = findViewById<RadioButton>(R.id.easyRadioButton)
        }
        defaultSetting!!.setChecked(true)
    }

    private fun createSwitchListener() {
        var gameModeSetting: Switch = findViewById<Switch>(R.id.gameModeSwitch)
        gameModeSetting.setOnClickListener( {// might need a view reference
            //gameModeSetting.text = "switch is "+ gameModeSetting.isChecked
            var pref = getSharedPreferences("fastmathtrainer",0).edit()
            if (gameModeSetting.isChecked) {
                // pref.putString("difficulty", "easy")
                pref.putString("gameMode", "timed")
            }
            else {
                pref.putString("gameMode", "practice")
            }
            pref.putString("restartActivity", "true")
            pref.commit()

            recreate()
        })
    }

}
