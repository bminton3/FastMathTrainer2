package com.minton.fastmathtrainer.Menus

import android.content.SharedPreferences
import android.os.Bundle
import android.widget.*
import com.google.android.gms.ads.AdView
import com.minton.fastmathtrainer.Generic.*
import com.minton.fastmathtrainer.R
import com.minton.fastmathtrainer.Style.StyleHandler

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
        val practiceGameLength = pref.getInt(practiceGameLength, practiceGameShort)

        setContentView(R.layout.activity_settings)
        // select the right radio buttons from saved settings
        setDifficultyRadioButtonFromPrefs(difficulty)
        setGameModeSwitchFromPrefs(gameMode)
        setGameDurationRadioButtonFromPrefs(gameDuration)
        setPracticeGameLengthFromPrefs(practiceGameLength)

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

        val practiceSetting : RadioGroup = findViewById<RadioGroup>(R.id.practiceRadioButtonGroup)
        when (practiceSetting.checkedRadioButtonId) {
            R.id.shortPracticeRadioButton -> pref.putInt(practiceGameLength, practiceGameShort)
            R.id.mediumPracticeRadioButton -> pref.putInt(practiceGameLength, practiceGameMedium)
            R.id.longPracticeRadioButton -> pref.putInt(practiceGameLength, practiceGameLong)
        }

        pref.commit()
        //Log.i("onPause() called.", " Saving state to " + pref)
    }

    private fun setGameModeSwitchFromPrefs(gameMode : String) {
        var gameModeSetting: Switch = findViewById<Switch>(R.id.gameModeSwitch)
        gameModeSetting.isChecked = gameMode.equals("timed")
    }

    private fun setPracticeGameLengthFromPrefs(length : Int) {
        var defaultSetting : RadioButton? = null
        when (length) {
            practiceGameShort -> defaultSetting = findViewById<RadioButton>(R.id.shortPracticeRadioButton)
            practiceGameMedium -> defaultSetting = findViewById<RadioButton>(R.id.mediumPracticeRadioButton)
            practiceGameLong -> defaultSetting = findViewById<RadioButton>(R.id.longPracticeRadioButton)
            else -> defaultSetting = findViewById<RadioButton>(R.id.shortPracticeRadioButton)
        }
        defaultSetting!!.setChecked(true)
    }

    private fun setGameDurationRadioButtonFromPrefs(duration : Int) {
        var defaultSetting : RadioButton? = null
        when (duration) {
            gameDurationShort -> defaultSetting = findViewById<RadioButton>(R.id.shortDurationRadioButton)
            gameDurationMedium -> defaultSetting = findViewById<RadioButton>(R.id.mediumDurationRadioButton)
            gameDurationLong -> defaultSetting = findViewById<RadioButton>(R.id.longDurationRadioButton)
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
