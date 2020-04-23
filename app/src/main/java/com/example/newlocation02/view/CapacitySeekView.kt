package com.example.newlocation02.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.lc.basemvp.out
import com.qmuiteam.qmui.util.QMUIDisplayHelper
import java.lang.IllegalArgumentException

class CapacitySeekView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private var seekWidth = 0
    private var seekHigh = 0
    private val defaultWidth = QMUIDisplayHelper.dp2px(context,25)
    private val defaultHight = QMUIDisplayHelper.dp2px(context,20)
    var mStrokeWidth = 15F
    private val rect by lazy { RectF(0F+mStrokeWidth,0F+mStrokeWidth, seekWidth-0F, seekHigh-0F) }
    private var totalPaint:Paint = Paint().apply { color = Color.GRAY
    style = Paint.Style.STROKE
    strokeWidth = mStrokeWidth
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        "当前的布局是左边：$left,左边：$right,上边：$top,下边$bottom".out()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        setMeasuredDimension(measureWidth(widthMeasureSpec),measureHight(heightMeasureSpec))
    }

    private fun measureWidth(widthMeasureSpec: Int):Int
    {
        val size = MeasureSpec.getSize(widthMeasureSpec)
        when(MeasureSpec.getMode(widthMeasureSpec))
        {
            MeasureSpec.EXACTLY ->{
                return size.coerceAtLeast(defaultWidth)
            }
            MeasureSpec.AT_MOST ->{
               return size.coerceAtMost(QMUIDisplayHelper.getScreenWidth(context)/3)
            }
        }
        return defaultWidth
    }

    private fun measureHight(widthMeasureSpec: Int):Int
    {
        val size = MeasureSpec.getSize(widthMeasureSpec)
        when(MeasureSpec.getMode(widthMeasureSpec))
        {
            MeasureSpec.EXACTLY ->{
                return size.coerceAtLeast(defaultHight)
            }
            MeasureSpec.AT_MOST ->{
             return size
            }
        }
        return defaultHight
    }


    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        "当前的尺寸变化了，之前是宽：$oldw,高：$oldh,现在是宽：$w,高：$h".out()
        seekWidth = (w-mStrokeWidth).toInt()
        seekHigh = h/10
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        "开始绘制了的".out()
        canvas?.run{
            drawRoundRect(rect,15F,15F,totalPaint)
        }
    }

}