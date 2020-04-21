package com.example.newlocation02.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.View.MeasureSpec.EXACTLY
import android.widget.TextView
import com.lc.basemvp.out
import com.qmuiteam.qmui.util.QMUIDisplayHelper
import com.qmuiteam.qmui.widget.popup.QMUIPopup
import com.qmuiteam.qmui.widget.popup.QMUIPopups
import android.view.View.MeasureSpec.AT_MOST as AT_MOST

//typealias click a:VIew->Unit

class PopColorView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0,
    var color: Int = Color.LTGRAY
) : View(context, attrs, defStyleAttr) {

    private val baseWidth = QMUIDisplayHelper.dp2px(this.context,80)
    private val baseHeight = QMUIDisplayHelper.dp2px(this.context,80)
    private var paint: Paint
    var showPop = true
    var popIsShow = false
    var radius = QMUIDisplayHelper.dp2px(context, 12)
        set(value) {
            field = QMUIDisplayHelper.dp2px(context, value)
        }

    var centerX: Float = 0f
    var centerY: Float = 0f
    lateinit var pop:QMUIPopup
    init {
        paint = Paint(Paint.ANTI_ALIAS_FLAG)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        setMeasuredDimension(measureWidth(widthMeasureSpec),measureHeight(heightMeasureSpec))
    }

    fun measureWidth(widthMeasureSpec: Int):Int{
        var size = MeasureSpec.getSize(widthMeasureSpec)
        when(MeasureSpec.getMode(widthMeasureSpec))
        {
            EXACTLY,AT_MOST ->{
               size =  size.coerceAtMost(baseWidth)
            }
            MeasureSpec.UNSPECIFIED -> {

            }
        }
        return size
    }
    fun measureHeight(heightMeasureSpec: Int):Int{
        var size = MeasureSpec.getSize(heightMeasureSpec)
        when(MeasureSpec.getMode(heightMeasureSpec))
        {
            EXACTLY,AT_MOST ->{
               size =  size.coerceAtMost(baseHeight)
            }
            MeasureSpec.UNSPECIFIED -> {

            }
        }
        return size
    }

    fun setClickListener(secondClick:(view:View)->Unit)
    {

        setOnClickListener{
            secondClick(it)
            if(!popIsShow) {
                pop = QMUIPopups.popup(context).arrow(true).arrowSize(15, 20)
                    .preferredDirection(QMUIPopup.DIRECTION_TOP)
                    .view(TextView(context).apply { text = "当前的颜色是${color}" })
                    .onDismiss { popIsShow = false }
                    .animStyle(QMUIPopup.ANIM_GROW_FROM_CENTER).show(it)
                popIsShow = true
            }
        }
    }


    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        centerX = (width / 2).toFloat()
        centerY = (height / 2).toFloat()
        centerX.out()
        centerY.out()
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        paint.color = color
        canvas?.let {
            it.drawCircle(centerX, centerY, radius.toFloat(), paint)
        }
    }


}