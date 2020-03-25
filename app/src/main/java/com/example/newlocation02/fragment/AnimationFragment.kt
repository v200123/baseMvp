package com.example.newlocation02.fragment

import android.animation.Keyframe
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.animation.ValueAnimator
import android.util.Log
import com.example.newlocation02.R
import com.example.newlocation02.mvp.IAnimationView
import com.example.newlocation02.mvp.presenter.AnimationPresenter
import com.lc.basemvp.BaseFragment
import com.qmuiteam.qmui.util.QMUIDisplayHelper
import com.qmuiteam.qmui.util.QMUIStatusBarHelper
import kotlinx.android.synthetic.main.fragment_animation.*

class AnimationFragment : BaseFragment<IAnimationView, AnimationPresenter>() {
    override fun createPresenter(): AnimationPresenter  = AnimationPresenter()

    override fun getLayoutId(): Int  = R.layout.fragment_animation

    override fun initData() {

    }

    override fun initView() {
        startAnimation()
    }

    private fun startAnimation() {
        val ofKeyframe = PropertyValuesHolder.ofKeyframe("translationX", Keyframe.ofFloat(40f, 55f))
        ObjectAnimator.ofFloat(button_animatioin01,"translationX",0f
            ,QMUIDisplayHelper.getScreenWidth(context).toFloat()/2,QMUIDisplayHelper.getScreenWidth(context).toFloat()).apply {
            duration = 6000
            start()
        }

//        val toFloat=
//            (QMUIDisplayHelper.getScreenHeight(context) - button_animatioin01.height - QMUIStatusBarHelper.getStatusbarHeight(
//                context
//            )).toFloat()
//        button_animatioin01.setOnClickListener{
////            it.animate().alpha(0f).setDuration(5000).rotation(360f)
//            ValueAnimator.ofFloat(it.translationY,
//                toFloat,toFloat/4,toFloat,toFloat/2
//            )
//                .apply {
//                    duration = 5000
//                    repeatCount = 2
//                    repeatMode = ValueAnimator.RESTART
//                    addUpdateListener { button_animatioin01.translationY = it.animatedValue as Float
//                    Log.d("动画","当前的值为${it.animatedValue}")}
//                }.start()
//        }
    }

    override fun showMsg(msg: String) {
        TODO("Not yet implemented")
    }
}