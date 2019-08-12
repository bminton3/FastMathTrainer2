package com.minton.fastmathtrainer.Menus

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import com.minton.fastmathtrainer.MathCards.*
import com.minton.fastmathtrainer.R
import android.view.animation.AlphaAnimation
import android.widget.TextView
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.AdRequest
import com.minton.fastmathtrainer.Generic.BaseActivity
import com.minton.fastmathtrainer.Generic.gameMode
import com.minton.fastmathtrainer.Generic.settingsActivity

import com.minton.fastmathtrainer.Style.StyleHandler


/**
 * MAIN activity
 */
class MainMenuActivity : BaseActivity() {

    lateinit var mAdView : AdView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // set the bg color
        var pref = getSharedPreferences("fastmathtrainer",0)
        val gameMode = pref.getString(gameMode, "practice")

        setContentView(R.layout.activity_main_menu)
        StyleHandler().runAnimatedBackground(findViewById(R.id.baseLayout), gameMode)
        createButtonListeners()

        // load ads
//        MobileAds.initialize(this) {}
//        mAdView = findViewById(R.id.adView)
//        val adRequest = AdRequest.Builder().build()
//        mAdView.loadAd(adRequest)
    }

    override fun onResume() {
        super.onResume()
        var pref = getSharedPreferences("fastmathtrainer",0)
        // pref.putString("restartActivity", "true")
        var restartActivity = pref.getString("restartActivity", "false")
        if (restartActivity.equals("true")) {
            var pref = getSharedPreferences("fastmathtrainer",0).edit()
            pref.putString("restartActivity", "false")
            pref.commit()
            //Log.i("MainMenuActivity", "recreating activity")
            recreate()
        }
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
            this.startActivityForResult(settingsIntent, settingsActivity)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == settingsActivity) {
            val savedSettings: TextView = findViewById<TextView>(R.id.savedSettings)
            savedSettings.visibility = View.VISIBLE
            val fadeIn = AlphaAnimation(0.0f, 1.0f)
            val fadeOut = AlphaAnimation(1.0f, 0.0f)
            savedSettings.startAnimation(fadeIn)
            savedSettings.startAnimation(fadeOut)
            fadeIn.duration = 600
            fadeIn.fillAfter = true
            fadeOut.duration = 600
            fadeOut.fillAfter = true
            fadeOut.startOffset = 1000 + fadeIn.startOffset
            savedSettings.visibility = View.INVISIBLE
        }
    }

    override fun onBackPressed() {
        moveTaskToBack(true)
    }
}
