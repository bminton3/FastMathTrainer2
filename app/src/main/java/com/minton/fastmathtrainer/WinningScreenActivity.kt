package com.minton.fastmathtrainer

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.TextView


class WinningScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_winning_screen)

        val intent = intent

        val message = intent.getStringExtra("MESSAGE")
        val time = intent.getStringExtra("TIME")

        val messageText: TextView = findViewById<TextView>(R.id.message)
        val timeText: TextView = findViewById<TextView>(R.id.time)

        messageText.text = message
        timeText.text = time
    }
}
