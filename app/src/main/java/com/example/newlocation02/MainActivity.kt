package com.lc.newlocation

import android.content.Context
import com.example.newlocation02.R
//import com.baidu.mapapi.map.MapView
//import com.lc.mvp.BaseActivity
import com.lc.newlocation.mvp.IMainView
import com.lc.newlocation.mvp.presenter.IMainPresenter

class MainActivity : BaseActivity<IMainView, IMainPresenter>() {
//    private lateinit var mBaiduMap: MapView
//    private var mapFragment: MapFragment = MapFragment()
//    private val blueTooth:Fragment by lazy { BluetoothStudyFragment() }

    override fun createPresenter(): IMainPresenter = IMainPresenter()

    override fun getContext(): Context = this

    override fun getLayoutId(): Int  = R.layout.activity_main
    override fun initView() {
    }

    override fun initData() {
    }


}
