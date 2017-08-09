package com.minton.fastmathtrainer.Menus

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import com.minton.fastmathtrainer.MathCards.*
import com.minton.fastmathtrainer.R

class MainMenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)

        createButtonListeners()
    }

    open fun createButtonListeners() {
        val add : Button = findViewById<Button>(R.id.add)
        add.setOnClickListener {
            val addIntent = Intent(this, AdditionCardsActivity::class.java)
            this.startActivity(addIntent)
        }

        val subtract : Button = findViewById<Button>(R.id.subtract)
        subtract.setOnClickListener {
            val subtractIntent = Intent(this, SubtractionCardsActivity::class.java)
            this.startActivity(subtractIntent)
        }

        val multiply : Button = findViewById<Button>(R.id.multiplication)
        multiply.setOnClickListener {
            val multiplyIntent = Intent(this, MultiplicationCardsActivity::class.java)
            this.startActivity(multiplyIntent)
        }

        val division : Button = findViewById<Button>(R.id.division)
        division.setOnClickListener {
            val divisionIntent = Intent(this, DivisionCardsActivity::class.java)
            this.startActivity(divisionIntent)
        }

        val combo : Button = findViewById<Button>(R.id.combo)
        combo.setOnClickListener {
            val comboIntent = Intent(this, CombinationCardsActivity::class.java)
            this.startActivity(comboIntent)
        }

        val settings : ImageButton = findViewById<ImageButton>(R.id.settings)
        settings.setOnClickListener {
            val settingsIntent = Intent(this, SettingsActivity::class.java)
            this.startActivity(settingsIntent)
        }
    }
}
