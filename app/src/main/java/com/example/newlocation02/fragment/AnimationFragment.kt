package com.example.newlocation02.fragment

import android.animation.Keyframe
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.RotateAnimation
import androidx.room.Room
import com.chad.library.adapter.base.BaseQuickAdapter
import com.example.newlocation02.R
import com.example.newlocation02.entity.MyHomeWork
import com.example.newlocation02.entity.database.HomeWorkDataBase
import com.example.newlocation02.mvp.IAnimationView
import com.example.newlocation02.mvp.presenter.AnimationPresenter
import com.lc.basemvp.BaseFragment
import com.qmuiteam.qmui.util.QMUIDisplayHelper
import kotlinx.android.synthetic.main.fragment_animation.*

class AnimationFragment : BaseFragment<IAnimationView, AnimationPresenter>() {

    val homeWorkDataBase by lazy {
        Room.databaseBuilder(context, HomeWorkDataBase::class.java, "my_home.db")
            .allowMainThreadQueries()
            .build()
    }
    val mMyHomeWorkDao by lazy { homeWorkDataBase.getHomeWork()}
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
        startAnimation()

    }

    private fun startAnimation() {
//        val apply = RotateAnimation(
//            0f,
//            360f,
//            Animation.RELATIVE_TO_SELF,
//            0.5f,
//            Animation.RELATIVE_TO_SELF,
//            0.5f
//        )
//            .apply {
//                duration = 1500
//                fillAfter = false
//            }
        val loadAnimation = AnimationUtils.loadAnimation(context, R.anim.animation_entry_01)
//            button_animatioin01.animation = apply
            button_animatioin01.startAnimation(loadAnimation)

//        val ofKeyframe = PropertyValuesHolder.ofKeyframe("translationX", Keyframe.ofFloat(40f, 55f))
//        ObjectAnimator.ofFloat(
//            button_animatioin01,
//            "translationX",
//            0f
//            ,
//            QMUIDisplayHelper.getScreenWidth(context).toFloat() / 2,
//            QMUIDisplayHelper.getScreenWidth(context).toFloat()
//        ).apply {
//            duration = 6000
//            start()
//        }

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