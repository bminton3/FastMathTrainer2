package com.minton.fastmathtrainer.Style

import android.graphics.drawable.AnimationDrawable
import android.view.ViewGroup


class StyleHandler : StyleHandlerIfc  {

    override fun runAnimatedBackground(layout: ViewGroup, gameMode: String) {
        val animationDrawable = layout.background as AnimationDrawable
        animationDrawable.setEnterFadeDuration(2000)
        animationDrawable.setExitFadeDuration(4000)
        animationDrawable.start()
    }

}