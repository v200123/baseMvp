package com.example.newlocation02.view

import android.content.Context
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.MotionEvent.ACTION_DOWN
import android.view.MotionEvent.ACTION_MOVE
import android.widget.LinearLayout
import android.widget.Scroller
import android.widget.TextView
import com.lc.basemvp.out
import com.qmuiteam.qmui.widget.popup.QMUIPopups

class PopLinearLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    //初始按下的X坐标
    private var mDownViewX = 0

    //    初始按下的Y坐标
    private var mDownViewY = 0
    private var rightBorder: Int = 0
    private var leftBorder: Int = 0
    private var scroller = Scroller(context)
    private var scrolleredDistance = 0.0f
    private var isFilling = false
    private var fillingDistance = 0
    private var childWidth = 0
    private var viewList = ArrayList<PopColorView>()
   private var oldPosition = -1
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
                        "原始的滑动距离${scrollX}" +
                        "是否越界$isFilling").out()

                isFilling = if (scrollX < 0 || scrollX > rightBorder) {
                    scrollBy(distanceX.toInt() / 5, 0)
                    true
                } else {
                    scrollBy(distanceX.toInt(), 0)
//                    "当前是第几个图标${}".out()

                    showPop(scrollX / childWidth)

                    false
                }
                return true
            }

            override fun onFling(
                e1: MotionEvent?,
                e2: MotionEvent?,
                velocityX: Float,
                velocityY: Float
            ): Boolean {
                "Fing被执行了的".out()
                if (isFilling) {
                    isFilling = false
                    if (scrollX < 0) scroller.startScroll(0, 0, 0, 0)
                    else scroller.startScroll(0, 0, rightBorder, 0)
                    invalidate()
                }
                return true
            }
            //        override fun onSingleTapUp(e: MotionEvent?): Boolean {
        })

    fun showPop(position: Int) {

        if (oldPosition != position) {
            oldPosition = position
            viewList[position].apply {
                QMUIPopups.popup(context).view(TextView(context).run {
                    text = "当前的颜色为${this@apply.color}"
                    this
                }).arrow(true).arrowSize(20, 30).show(this)
            }
        }


    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        for (i in 0 until childCount) {
            measureChild(getChildAt(i), widthMeasureSpec, heightMeasureSpec)
        }
    }


    override fun onTouchEvent(event: MotionEvent?): Boolean {

//        when (event!!.action) {
//            ACTION_DOWN -> {
//                mDownViewX = event.rawX.toInt()
//                mDownViewY = event.rawY.toInt()
//            }
//            ACTION_MOVE -> {
//
//            }
//        }
        mGestureDetector.onTouchEvent(event)
        return true
    }

    fun addColorList(list: MutableList<Int>) {
        list.forEach {
            val popColorView = PopColorView(this.context, color = it)
            viewList.add(popColorView)
            addView(popColorView)
        }
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
        rightBorder = getChildAt(childCount - 2).right
        "哈哈哈哈哈哈$leftBorder".out()
        rightBorder.out()
    }

}