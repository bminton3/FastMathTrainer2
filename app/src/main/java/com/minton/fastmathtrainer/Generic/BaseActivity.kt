package com.minton.fastmathtrainer.Generic

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import com.minton.fastmathtrainer.R


open class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var pref = getSharedPreferences("fastmathtrainer",0)
        val gameMode = pref.getString(gameMode, "practice")
        Log.i("BaseActivity", "game mode: " + gameMode)
        if (gameMode.equals("timed")) {
            setTheme(R.style.TimedTheme)
        }
        else {
            setTheme(R.style.AppTheme)
        }
    }

}
