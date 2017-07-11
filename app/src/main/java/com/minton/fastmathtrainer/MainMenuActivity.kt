package com.minton.fastmathtrainer

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import kotlinx.android.synthetic.main.activity_math_cards.*

class MainMenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)

        val nineButton : Button = findViewById<Button>(R.id.add)
        nineButton.setOnClickListener {
            val addIntent = Intent(this, MathCardsActivity::class.java)
            this.startActivity(addIntent)
        }
    }
}
