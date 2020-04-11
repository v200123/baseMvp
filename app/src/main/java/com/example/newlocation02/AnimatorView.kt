package com.example.newlocation02

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.View

class AnimatorView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private var radius = 100f
    var painColor:Int = 0x0
    set(value) {
        paint.color = value
        field = value
        invalidate()
        Log.d("动画","动画数值更新了当前是$value")
    }
    private var paint: Paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color  = Color.BLUE
    }



    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawCircle(500f,500f,radius,paint)
    }


}