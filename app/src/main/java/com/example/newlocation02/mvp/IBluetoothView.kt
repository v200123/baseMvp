package com.lc.newlocation.mvp

import com.lc.basemvp.IBaseFragmentView
import com.lc.newlocation.bean.BlueToothBean

/**
@packageName com.lc.newlocation.mvp
@author admin
@date 2020/3/13
 */
interface IBluetoothView :IBaseFragmentView {
    fun showError(information:String)
    fun registerBroadcast()
    fun addList(blueToothBean: BlueToothBean)
    fun showInformation(information: String)

}