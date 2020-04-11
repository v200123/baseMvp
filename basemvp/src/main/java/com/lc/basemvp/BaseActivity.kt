package com.lc.basemvp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

fun <T>T.out(){
    Log.d("日志输出",this.toString())
}

abstract class BaseActivity<V : IBaseView,P : BasePresenter<V>> : AppCompatActivity(),IBaseView  {

    private val  mPresenter:P by lazy {
        createPresenter()
    }

    abstract fun createPresenter():P

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        "当前的设备是\t${this::class.java.simpleName}".out()
        initView()
        mPresenter.attachView(this as V)
        if(savedInstanceState!=null)
        initData()
        else
            replyData()
    }

    /**
     * 如果保存的有数据，则直接进行恢，而不进行初始化数据
     */
    open fun replyData()
    {}

    override fun onRestart() {
        super.onRestart()
        exitButEnter()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        saveAbnormalExitData(outState)
    }

    override fun showLoading() {
        TODO("Not yet implemented")
    }

    override fun showMsg(msg: String) {
        TODO("Not yet implemented")
    }

    override fun hideLoading() {
        TODO("Not yet implemented")
    }
    /**
     * 用于应用由于屏幕翻转等原因导致的数据丢失的保存策略
     */
    open fun saveAbnormalExitData(outState: Bundle)
    {}

    /**
     * 可以在此方法下写关于Activity暂时被其他应用遮挡继而导致Activity的生命周期从onRestart()开始
     */
    open fun exitButEnter()
    {

    }

    abstract fun getLayoutId():Int
}