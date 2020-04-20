package com.example.newlocation02.view

import android.content.Context
import android.media.midi.MidiOutputPort
import android.text.method.Touch
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.MotionEvent.*
import android.widget.LinearLayout
import android.widget.Scroller
import com.lc.basemvp.out

class PopLinearLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private var rightBorder: Int = 0
    private var leftBorder: Int = 0
    private var scroller = Scroller(context)
    private var scrolleredDistance = 0.0f
    private var isFilling = false
    private var fillingDistance = 0
    private var childWidth = 0
    private val mGestureDetector =
        GestureDetector(context, object :
            GestureDetector.SimpleOnGestureListener() {
            override fun onScroll(
                e1: MotionEvent?,
                e2: MotionEvent?,
                distanceX: Float,
                distanceY: Float
            ): Boolean {
                ("当前第一个初始点击类型是${e1!!.action}" +
                        "当前第二个参数的点击类型是${e2!!.action}" +
                        "原始的滑动距离${ scrollX}" +
                        "是否越界$isFilling").out()
                scrollBy(distanceX.toInt(), 0)
                if(scrollX<0||scrollX>rightBorder)
                {
                    isFilling = true
                }else{
                    "当前是第几个图标${scrollX/childWidth.toFloat()}".out()
                }
                return true
            }
            override fun onFling(
                e1: MotionEvent?,
                e2: MotionEvent?,
                velocityX: Float,
                velocityY: Float
            ): Boolean {
                if(isFilling)
                {
                    isFilling = false
                    if(scrollX<0) scroller.startScroll(0,0,0,0)
                    else scroller.startScroll(scrollX,0,rightBorder/3,0)
                    invalidate()

                }
                return true
            }
            //        override fun onSingleTapUp(e: MotionEvent?): Boolean {
        })

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        for (i in 0 until childCount) {
            measureChild(getChildAt(i), widthMeasureSpec, heightMeasureSpec)
        }
    }


    override fun onTouchEvent(event: MotionEvent?): Boolean {

//        when(event!!.action)
//        {
//            ACTION_MOVE ->{
//                scrollTo()
//            }
//        }
            mGestureDetector.onTouchEvent(event)
        return true
    }

    fun addColorList(list: MutableList<Int>){
        list.forEach { addView(PopColorView(this.context,color = it)) }
        invalidate()
    }


    override fun computeScroll() {
        super.computeScroll()
        if (scroller.computeScrollOffset()) {
            scrollTo(scroller.currX, scroller.currY)
            invalidate()
        }
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        super.onLayout(changed, l, t, r, b)
        if (changed) {
            for (i in 0 until childCount) {
                childWidth = childWidth.coerceAtLeast(getChildAt(i).width)
                getChildAt(i).layout(
                    i * getChildAt(i).width,
                    0,
                    (i + 1) * getChildAt(i).width,
                    getChildAt(i).height
                )
            }
        }
        leftBorder = getChildAt(0).left
        rightBorder = getChildAt(childCount - 1).right
        layoutParams.width = rightBorder - leftBorder
        "哈哈哈哈哈哈$leftBorder".out()
        rightBorder.out()
    }

}