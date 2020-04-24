package com.example.newlocation02.fragment

import android.graphics.Color
import android.widget.Toast
import com.example.newlocation02.R
import com.example.newlocation02.mvp.CreateIndicatorView
import com.example.newlocation02.mvp.presenter.CreateIndicatorPresenter
import com.lc.basemvp.BaseFragment
import com.lc.basemvp.out
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.fragment_indicator.*
import java.util.*

class CreateIndicatorFragment : BaseFragment<CreateIndicatorView, CreateIndicatorPresenter>() {
    override fun createPresenter(): CreateIndicatorPresenter = CreateIndicatorPresenter()
    private var timer:Timer = Timer()
    private var progressRate = 0.00F
    val timerTask = object :TimerTask() {
        override fun run() {
            progressRate+=0.2F
            seek.progressRate =progressRate
        }
    }
    override fun getLayoutId(): Int = R.layout.fragment_indicator

    override fun initView() {
//        colorCaptation.addColorList(mutableListOf(Color.GRAY, Color.CYAN, Color.LTGRAY, Color.RED))
//        timer.schedule(timerTask,2000,1000)
        seek.progressRate = progressRate
    }

    override fun initData() {
    }

    override fun showMsg(msg: String) {
        Toasty.error(mContext, msg).show()
    }
}