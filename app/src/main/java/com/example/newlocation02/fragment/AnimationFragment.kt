package com.example.newlocation02.fragment

import android.animation.ObjectAnimator
import android.animation.TypeEvaluator
import android.graphics.Color
import android.util.Log
import androidx.navigation.NavAction
import androidx.navigation.Navigation
import com.example.newlocation02.R
import com.example.newlocation02.mvp.IAnimationView
import com.example.newlocation02.mvp.presenter.AnimationPresenter
import com.lc.basemvp.BaseFragment
import kotlinx.android.synthetic.main.fragment_animation.*
import kotlin.math.abs
import kotlin.math.roundToInt

class AnimationFragment : BaseFragment<IAnimationView, AnimationPresenter>() {

    //    val homeWorkDataBase by lazy {
//        Room.databaseBuilder(context, HomeWorkDataBase::class.java, "my_home.db")
//            .allowMainThreadQueries()
//            .build()
//    }
//    val mMyHomeWorkDao by lazy { homeWorkDataBase.getHomeWork()}
    override fun createPresenter(): AnimationPresenter = AnimationPresenter()

    override fun getLayoutId(): Int = R.layout.fragment_animation

    override fun initData() {
        addHomeWork()
    }

    override fun onStart() {
        super.onStart()
        getAllHomeWork()
    }

    private fun getAllHomeWork() {
//        mMyHomeWorkDao.getAllHomeWork().forEach { Log.d("我的作业","${it.id}") }
    }

    private fun addHomeWork() {
//        for(i in 1 until 100)
//        mMyHomeWorkDao.inserHomeWork(MyHomeWork(i,"张三","10点"))
    }

    override fun initView() {
        button_animatioin01.setOnClickListener {
//            ObjectAnimator.ofObject(MyAnimatorView,"painColor",MyTypeEvaluator(),MyAnimatorView.painColor,0xFFFFFF).apply {
//                duration = 10000
//                start()
            Navigation.findNavController(it).navigate(R.id.action_animationFragment_to_playMusicFragment)
            }
        }



    override fun showMsg(msg: String) {
        Log.e("出错了", msg)
    }
}

class MyTypeEvaluator : TypeEvaluator<Int> {
    override fun evaluate(fraction: Float, startValue: Int?, endValue: Int?): Int {
        val startBlue = Color.blue(startValue!!)
        val startRed = Color.red(startValue)
        val startGreen = Color.green(startValue)
        var endBlue = Color.blue(endValue!!)
        var endRed = Color.red(endValue)
        var endGreen = Color.green(endValue)

        val redDiff = abs(startRed - endRed);
        val greenDiff = abs(startGreen - endGreen);
        val blueDiff = abs(startBlue - endBlue);
        val colorDiff = redDiff + greenDiff + blueDiff;
        when {
            startRed != endRed -> {
                endRed = getCurrentColor(startRed, endRed, colorDiff, redDiff,
                    fraction);
                // getCurrentColor()决定如何根据差值来决定颜色变化的快慢 ->>关注1
            }
            startGreen != endGreen -> {
                endGreen = getCurrentColor(startGreen, endGreen, colorDiff,
                    redDiff, fraction);
            }
            startBlue != endBlue -> {
                endBlue = getCurrentColor(startBlue, endBlue, colorDiff,
                    redDiff + greenDiff, fraction);
            }
        }
        return Color.rgb(endRed,endGreen,endBlue)
    }

    private fun getCurrentColor(
        startColor: Int,
        endColor: Int,
        colorDiff: Int,
        offset: Int,
        fraction: Float
    ): Int {
        var currentColor: Int
        if (startColor > endColor) {
            currentColor =  ((startColor - (fraction * colorDiff - offset)).roundToInt());
            if (currentColor < endColor) {
                currentColor = endColor;
            }
        } else {
            currentColor =((startColor + (fraction * colorDiff - offset)).roundToInt());
            if (currentColor > endColor) {
                currentColor = endColor;
            }
        }
        return currentColor

    }


}