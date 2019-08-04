package com.minton.fastmathtrainer.Generic

import android.os.Bundle
import android.content.Intent
import android.widget.Button
import android.widget.TextView
import com.minton.fastmathtrainer.Menus.MainMenuActivity
import com.minton.fastmathtrainer.R
import com.minton.fastmathtrainer.Style.StyleHandler


class WinningScreenActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        // set the bg color - this used to set the bg color. Not anymore. Can we remove?
        var pref = getSharedPreferences("fastmathtrainer",0)
        val gameMode = pref.getString(gameMode, "practice")

        setContentView(R.layout.activity_winning_screen)

        fillWinningTextValues()

        val callingActivity = callingActivity.className

        StyleHandler().runAnimatedBackground(findViewById(R.id.baseLayout), gameMode)

        /** buttons - need a better way to code this */
        val menu : Button = findViewById<Button>(R.id.menu)
        menu.setOnClickListener {
            val addIntent = Intent(this, MainMenuActivity::class.java)
            this.startActivity(addIntent)
        }

        val reset : Button = findViewById<Button>(R.id.reset)
        reset.setOnClickListener {
            val addIntent = Intent(this, Class.forName(callingActivity))
            this.startActivity(addIntent)
        }
    }

    override fun onBackPressed() {
        val addIntent = Intent(this, MainMenuActivity::class.java)
        this.startActivity(addIntent)
    }

    private fun fillWinningTextValues() {
        val intent = intent

        val message = intent.getStringExtra("MESSAGE")
        val time = intent.getStringExtra("TIME")

        val messageText: TextView = findViewById<TextView>(R.id.message)
        val timeText: TextView = findViewById<TextView>(R.id.time)

        messageText.text = message
        timeText.text = time
    }
}
