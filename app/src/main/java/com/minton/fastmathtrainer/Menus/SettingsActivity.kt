package com.minton.fastmathtrainer.Menus

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.*
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.minton.fastmathtrainer.Generic.*
import com.minton.fastmathtrainer.R
import com.minton.fastmathtrainer.Style.StyleHandler
import kotlinx.android.synthetic.main.activity_settings.*

/**
 *  TODO add game length setting and prompt user that's how many they need to answer.
 *  TODO store user speed in a local DB and keep track of user progress
 *  TODO have users sign in to compare their progress
 */
class SettingsActivity : BaseActivity() {

    protected lateinit var pref : SharedPreferences
    lateinit var mAdView : AdView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pref = getSharedPreferences("fastmathtrainer",0)

        val gameMode = pref.getString(gameMode, "practice")
        val difficulty = pref.getString(gameDifficulty, gameDifficultyEasy)
        val gameDuration = pref.getInt(gameDuration, gameDurationShort)

        setContentView(R.layout.activity_settings)
        setDifficultyRadioButtonFromPrefs(difficulty)
        setGameModeSwitchFromPrefs(gameMode)
        setGameDurationRadioButtonFromPrefs(gameDuration)
        createSwitchListener()
        val relativeLayout = findViewById(R.id.baseLayout) as RelativeLayout
        if (!gameMode.equals("timed")) {

            relativeLayout.setBackgroundResource(R.drawable.android_gradient_list)
        }
        else{
            relativeLayout.setBackgroundResource(R.drawable.android_gradient_timed_play)
        }
        StyleHandler().runAnimatedBackground(findViewById(R.id.baseLayout), gameMode)
        // load ads
//        MobileAds.initialize(this) {}
//        mAdView = findViewById(R.id.adView)
//        val adRequest = AdRequest.Builder().build()
//        mAdView.loadAd(adRequest)
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
            R.id.easyRadioButton -> pref.putString(gameDifficulty, gameDifficultyEasy)
            R.id.mediumRadioButton -> pref.putString(gameDifficulty, gameDifficultyMedium)
            R.id.hardRadioButton -> pref.putString(gameDifficulty, gameDifficultyHard)
        }

        val durationSetting : RadioGroup = findViewById<RadioGroup>(R.id.durationRadioButtonGroup)
        when (durationSetting.checkedRadioButtonId) {
            R.id.shortDurationRadioButton -> pref.putInt(gameDuration, gameDurationShort)
            R.id.mediumDurationRadioButton -> pref.putInt(gameDuration, gameDurationMedium)
            R.id.longDurationRadioButton -> pref.putInt(gameDuration, gameDurationLong)
        }

        pref.commit()
        //Log.i("onPause() called.", " Saving state to " + pref)
    }

    private fun setGameModeSwitchFromPrefs(gameMode : String) {
        var gameModeSetting: Switch = findViewById<Switch>(R.id.gameModeSwitch)
        gameModeSetting.isChecked = gameMode.equals("timed")
    }

    private fun setGameDurationRadioButtonFromPrefs(duration : Int) {
        var defaultSetting : RadioButton? = null
        when (duration) {
            15 -> defaultSetting = findViewById<RadioButton>(R.id.shortDurationRadioButton)
            30 -> defaultSetting = findViewById<RadioButton>(R.id.mediumDurationRadioButton)
            45 -> defaultSetting = findViewById<RadioButton>(R.id.longDurationRadioButton)
            else -> defaultSetting = findViewById<RadioButton>(R.id.shortDurationRadioButton)
        }
        defaultSetting!!.setChecked(true)
    }

    private fun setDifficultyRadioButtonFromPrefs(difficulty : String) {
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
                pref.putString(gameMode, "timed")
            }
            else {
                pref.putString(gameMode, "practice")
            }
            pref.putString("restartActivity", "true")
            pref.commit()

            recreate()
        })
    }

}
