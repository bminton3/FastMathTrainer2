package com.minton.fastmathtrainer.hacks

import android.content.Context
import android.util.TypedValue
import android.text.Layout
import android.text.TextUtils.TruncateAt
import android.util.AttributeSet
import android.widget.TextView



/**
 * Grabbed from https://stackoverflow.com/questions/5033012/auto-scale-textview-text-to-fit-within-bounds
 */
class SingleLineTextView : TextView {

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        setSingleLine()
        ellipsize = TruncateAt.END
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        setSingleLine()
        ellipsize = TruncateAt.END
    }

    constructor(context: Context) : super(context) {
        setSingleLine()
        ellipsize = TruncateAt.END
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        val layout = layout
        if (layout != null) {
            val lineCount = layout.lineCount
            if (lineCount > 0) {
                val ellipsisCount = layout.getEllipsisCount(lineCount - 1)
                if (ellipsisCount > 0) {

                    val textSize = textSize

                    // textSize is already expressed in pixels
                    setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize - 1)

                    super.onMeasure(widthMeasureSpec, heightMeasureSpec)
                }
            }
        }
    }

}