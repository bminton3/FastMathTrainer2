package com.minton.fastmathtrainer

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.minton.fastmathtrainer.MathCards.*

class MainMenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)

        val add : Button = findViewById<Button>(R.id.add)
        add.setOnClickListener {
            val addIntent = Intent(this, AdditionCardsActivity::class.java)
            this.startActivity(addIntent)
        }

        val subtract : Button = findViewById<Button>(R.id.subtract)
        subtract.setOnClickListener {
            val addIntent = Intent(this, SubtractionCardsActivity::class.java)
            this.startActivity(addIntent)
        }

        val multiply : Button = findViewById<Button>(R.id.multiplication)
        multiply.setOnClickListener {
            val addIntent = Intent(this, MultiplicationCardsActivity::class.java)
            this.startActivity(addIntent)
        }

        val division : Button = findViewById<Button>(R.id.division)
        division.setOnClickListener {
            val addIntent = Intent(this, DivisionCardsActivity::class.java)
            this.startActivity(addIntent)
        }

        val combo : Button = findViewById<Button>(R.id.combo)
        combo.setOnClickListener {
            val addIntent = Intent(this, CombinationCardsActivity::class.java)
            this.startActivity(addIntent)
        }
    }
}
