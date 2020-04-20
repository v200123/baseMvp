package com.example.newlocation02.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import androidx.core.view.forEach
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.lc.basemvp.out
import com.qmuiteam.qmui.util.QMUIDisplayHelper
import java.lang.IllegalArgumentException

class IndicatorLinearLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {
    val paint: Paint
    var radius: Float = 60.0f
    private var dotPitch: Int = QMUIDisplayHelper.dp2px(context, 20)
    private var currentPosition = 0
    private lateinit var colorList: MutableList<Int>


    fun setColorList(colorList: MutableList<Int>) {
        this.colorList = colorList
        layoutParams.width = (colorList.size * 2 * radius + (dotPitch * colorList.size + 1)).toInt()
        layoutParams.height = (radius * 2 + 80).toInt()
        invalidate()
    }

    private var viewPager: ViewPager2? = null
        set(value) {
            field = value
            field!!.apply {
                registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                    override fun onPageSelected(position: Int) {
                        super.onPageSelected(position)
                        currentPosition = position
                        invalidate()
                    }
                })
            }

        }

    init {
        setWillNotDraw(false)
        paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
            color = Color.GREEN
        }
    }


    fun scrollBy(position: Int, smoothScroller: Boolean) {

        viewPager?.setCurrentItem(position, smoothScroller)
            ?: throw IllegalArgumentException("ViewPager没有被加入")
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        "我开始绘制了的".out()
        colorList.let {
            for (i in 0 until it.size) {
                paint.color = it[i]
                if (i == 0) {
                    canvas?.drawCircle(radius, (radius + 80) / 2, radius, paint)
                } else
                    canvas?.drawCircle(
                        i * (2 * radius + dotPitch) + radius,
                        (radius + 80) / 2,
                        radius,
                        paint
                    )
            }
        }

    }


}