package com.minton.fastmathtrainer.Menus

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Switch
import com.minton.fastmathtrainer.R


class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        var pref = getPreferences(0)
        val difficulty = pref.getString("difficulty", "easy")
        val gameMode = pref.getString("gameMode", "practice")

        setSavedDifficulty(difficulty)
        setGameMode(gameMode)
    }

    override fun onPause() {
        super.onPause()
        val difficultySetting : RadioGroup = findViewById<RadioGroup>(R.id.radioButtonGroup)
        val difficultySelected = difficultySetting.checkedRadioButtonId
        var pref = getPreferences(0).edit()
        when (difficultySelected) {
            R.id.easyRadioButton -> pref.putString("difficulty", "easy")
            R.id.mediumRadioButton -> pref.putString("difficulty", "medium")
            R.id.hardRadioButton -> pref.putString("difficulty", "hard")
        }

        var gameModeSwitch : Switch = findViewById<Switch>(R.id.gameModeSwitch)
        val timed = gameModeSwitch.isChecked
        if (timed) {
            pref.putString("gameMode", "timed")
        }
        else {
            pref.putString("gameMode", "practice")
        }

        pref.commit()
        Log.i("onPause() called. Saving state to " + difficultySelected, "wtf")
    }

    private fun setGameMode(gameMode : String) {
        var defaultSetting : Switch = findViewById<Switch>(R.id.gameModeSwitch)
        defaultSetting.isChecked = gameMode.equals("timed")
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
}
