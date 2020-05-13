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
    private val defaultWidth = QMUIDisplayHelper.dp2px(context, 25)
    private val defaultHight = QMUIDisplayHelper.dp2px(context, 10)
    var mStrokeWidth = 15F
    var progressRate = 0.0F
    set(value) {field = value
    invalidate()}
    private val rect by lazy {
        RectF(
            0F + mStrokeWidth,
            0F + mStrokeWidth,
            seekWidth - 0F,
            seekHigh - 0F
        )
    }
    private var mUsedPaint = Paint().apply {
        color = Color.RED
        style = Paint.Style.FILL
    }

    private var totalPaint: Paint = Paint().apply {
        color = Color.GREEN
        style = Paint.Style.FILL
        strokeWidth = mStrokeWidth
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        "当前的布局是左边：$left,右边：$right,上边：$top,下边$bottom".out()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        setMeasuredDimension(measureWidth(widthMeasureSpec), measureHigh(heightMeasureSpec))
    }

    private fun measureWidth(widthMeasureSpec: Int): Int {
        val size = MeasureSpec.getSize(widthMeasureSpec)
        when (MeasureSpec.getMode(widthMeasureSpec)) {
            MeasureSpec.EXACTLY -> {
                return size.coerceAtLeast(defaultWidth)
            }
            MeasureSpec.AT_MOST -> {
                return size.coerceAtMost(QMUIDisplayHelper.getScreenWidth(context) / 3)
            }
        }
        return defaultWidth
    }

    private fun measureHigh(widthMeasureSpec: Int): Int {
        val size = MeasureSpec.getSize(widthMeasureSpec)
        when (MeasureSpec.getMode(widthMeasureSpec)) {
            MeasureSpec.EXACTLY, MeasureSpec.UNSPECIFIED -> {
                return size.coerceAtLeast(defaultHight)
            }
            MeasureSpec.AT_MOST -> {
                return defaultHight.coerceAtLeast(size)
            }
        }
        return defaultHight
    }


    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        "当前的尺寸变化了，之前是宽：$oldw,高：$oldh,现在是宽：$w,高：$h".out()
        seekWidth = measuredWidth
        seekHigh = h.coerceAtMost(defaultHight)
    }



    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        "开始绘制了的".out()
        canvas?.run {
            if(progressRate<1) {
                drawRoundRect(rect, 15F, 15F, totalPaint)
                drawRoundRect(
                    0F + mStrokeWidth, 0F + mStrokeWidth,
                    seekWidth * progressRate, seekHigh.toFloat(), 15F, 15F, mUsedPaint
                )
            }
        }
    }

}