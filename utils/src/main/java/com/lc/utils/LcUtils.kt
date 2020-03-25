package com.lc.utils

import android.content.Context

class LcUtils {

    companion object{
        lateinit var  context:Context
        var SPName:String = "com.lcUtils.SharedPreferences"
        fun init(context: Context){
            this.context = context
        }
    }
}