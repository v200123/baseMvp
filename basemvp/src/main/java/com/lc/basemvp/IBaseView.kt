package com.lc.basemvp

import android.content.Context

interface IBaseView {
    /**
     * 这个是用于初始化视图的，其执行是在初始化视图之前
     */
    fun initView()
    /**
     * 这个是用于初始化数据的，其执行是在初始化视图之后
     */
    fun initData()
    fun showLoading(message:String= "请稍后")
    fun getContext(): Context
    fun hideLoading()
    fun showMsg(msg:String)
}