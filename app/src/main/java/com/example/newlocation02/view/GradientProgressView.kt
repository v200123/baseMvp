package com.example.newlocation02.view

import android.animation.Animator
import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import androidx.core.view.marginStart
import com.example.newlocation02.BreathingInterpolator
import com.example.newlocation02.R

class GradientProgressView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private var mWidth = 0
    private var mHigh = 0
    private var mPaddingLeft = 0
    private var mPaddingRight = 0
    private var mPaddingTop = 0
    private var mPaddingBottom = 0
    private lateinit var mAnimator: ObjectAnimator
    private var mAnimalStatus = false
    private var startColor = 0
    private var endColor = 0
    private var mProccess: Float = 0f
        set(value) {
            field = value
            invalidate()
        }
    private var mOutPaint: Paint = Paint().apply {
        color = Color.BLACK
        style = Paint.Style.STROKE
        strokeWidth = 3f
    }

    init {
        context.obtainStyledAttributes(attrs, R.styleable.GradientProgressView)
            .apply {
                startColor = getColor(R.styleable.GradientProgressView_StartColor, 0x91E63)
                endColor = getColor(R.styleable.GradientProgressView_StartColor, 0x2196F3)
                recycle()
            }
    }

    private var mGradientPaint = Paint()

    fun updateProcess(position: Float) {
        if (mAnimalStatus) {
            mAnimator.end()
            mAnimalStatus = false
        }
        mAnimator = ObjectAnimator.ofFloat(this, "mProccess", mProccess, position)
            .apply {
                duration = 10000
                start()
                addListener(object : Animator.AnimatorListener {
                    override fun onAnimationRepeat(animation: Animator?) {

                    }

                    override fun onAnimationEnd(animation: Animator?) {
                        mAnimalStatus = false
                    }

                    override fun onAnimationCancel(animation: Animator?) {
                        mAnimalStatus = false
                    }

                    override fun onAnimationStart(animation: Animator?) {
                        mAnimalStatus = true
                    }

                })
            }

    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mWidth = w
        mHigh = h
        mPaddingLeft = paddingLeft
        mPaddingRight = paddingRight
        mPaddingTop = paddingTop
        mPaddingBottom = paddingBottom
        mGradientPaint.shader = LinearGradient(
            0 + mPaddingLeft.toFloat(), 0 + mPaddingTop.toFloat()
            , mWidth - mPaddingRight.toFloat(), mHigh - mPaddingBottom.toFloat(),
            startColor, Color.parseColor("#2196F3"),
            Shader.TileMode.REPEAT
        )

    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.apply {
            drawRoundRect(
                0 + mPaddingLeft.toFloat(),
                0 + mPaddingTop.toFloat(),
                mWidth - mPaddingRight.toFloat(),
                mHigh - mPaddingBottom.toFloat(),
                15f,
                15f,
                mOutPaint
            )
            drawRoundRect(
                0 + mPaddingLeft.toFloat(),
                0 + mPaddingTop.toFloat(),
                (mWidth - mPaddingRight) * mProccess,
                mHigh - mPaddingBottom.toFloat(),
                15f,
                15f,
                mGradientPaint
            )
        }
    }
}