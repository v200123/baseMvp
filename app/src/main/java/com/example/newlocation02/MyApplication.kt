package com.example.newlocation02

import android.app.Application
import android.content.Context
import com.lc.utils.LcUtils

/**
@packageName com.lc.newlocation
@author admin
@date 2020/3/9
 */
class MyApplication : Application() {
    


    override fun onCreate() {
        super.onCreate()
        LcUtils.init(this)
//        SDKInitializer.initialize(this)
//        SDKInitializer.setCoordType(CoordType.BD09LL)

//        OpenLogUtil.setModuleLogEnable(ModuleName.TILE_OVERLAY_MODULE,true)
    }

    companion object {
        private val MyContext:Context  = MyApplication()
        @JvmStatic fun getContext():Context{
            return MyContext
        }
    }


}