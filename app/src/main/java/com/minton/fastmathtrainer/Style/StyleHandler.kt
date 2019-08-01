package com.minton.fastmathtrainer.Style

import android.graphics.drawable.AnimationDrawable
import android.support.v7.app.AppCompatActivity
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RelativeLayout
import com.minton.fastmathtrainer.R


class StyleHandler : StyleHandlerIfc  {

    override fun runAnimatedBackground(layout: ViewGroup, gameMode: String) {

        if (!gameMode.equals("timed")) {
            val thisLayout : ViewGroup
            if (layout is LinearLayout) {
                thisLayout = layout as LinearLayout
            }
            else if (layout is RelativeLayout) {
                thisLayout = layout as RelativeLayout
            }
            else {
                thisLayout = layout as LinearLayout
            }
            val animationDrawable = thisLayout.background as AnimationDrawable
            animationDrawable.setEnterFadeDuration(2000)
            animationDrawable.setExitFadeDuration(4000)
            animationDrawable.start()
        }
    }

}