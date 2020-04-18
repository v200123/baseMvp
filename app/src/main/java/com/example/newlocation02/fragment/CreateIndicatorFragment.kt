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

class CreateIndicatorFragment : BaseFragment<CreateIndicatorView,CreateIndicatorPresenter>() {
    override fun createPresenter(): CreateIndicatorPresenter  = CreateIndicatorPresenter()

    override fun getLayoutId(): Int= R.layout.fragment_indicator

    override fun initView() {
        test_indicator.apply {
//            setColorList(mutableListOf(Color.BLACK,Color.BLUE,Color.CYAN,Color.DKGRAY,Color.GRAY))
            setClickListener { "我什么都不做".out() }
        }
    }

    override fun initData() {
    }

    override fun showMsg(msg: String){
        Toasty.error(mContext,msg).show()
    }
}