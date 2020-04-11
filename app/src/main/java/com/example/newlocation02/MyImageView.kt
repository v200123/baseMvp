package com.example.newlocation02

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import androidx.appcompat.widget.AppCompatImageView

class MyImageView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : AppCompatImageView(context, attrs, defStyleAttr) {

    val listener:GestureDetector.SimpleOnGestureListener by lazy {
        object : GestureDetector.SimpleOnGestureListener() {
            override fun onLongPress(e: MotionEvent?) {
                super.onLongPress(e)
                Log.d("长按事件","被触发了")
            }

        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val gestureDetector = GestureDetector(context, listener)
        return gestureDetector.onTouchEvent(event)

    }


}