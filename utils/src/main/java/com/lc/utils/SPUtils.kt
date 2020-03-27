package com.lc.utils

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import android.util.SparseArray
import com.lc.utils.LcUtils.Companion.SPName
import com.lc.utils.LcUtils.Companion.context
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

@Suppress("UNCHECKED_CAST")
class SPUtils<T>(private val defaultValue:T) {
    private val mSharePreferce:SharedPreferences by lazy {
       context.getSharedPreferences(SPName, Context.MODE_PRIVATE)
    }

    operator fun getValue(thisRef: T, property: KProperty<*>): T  = mSharePreferce.run {
        when(defaultValue)
        {
            is Int ->{return@run this.getInt(SPName,defaultValue)as T}
            is Log ->{return@run this.getLong(SPName,defaultValue as Long)as T}
            is String ->{return@run this.getString(SPName,defaultValue)as T}
            is Boolean ->{return@run this.getBoolean(SPName,defaultValue) as T}
            is Float ->{return@run this.getFloat(SPName,defaultValue)as T}
            else ->{throw IllegalArgumentException("这种类型无法保存")}
        }
    }

    operator fun setValue(thisRef: T, property: KProperty<*>, value: T) {
        with(mSharePreferce.edit()){
            when (value) {
                is Long -> putLong(SPName, value)
                is String -> putString(SPName, value)
                is Int -> putInt(SPName, value)
                is Boolean -> putBoolean(SPName, value)
                is Float -> putFloat(SPName, value)
                else -> throw IllegalArgumentException("This type of data cannot be saved!")
            }.apply()
        }
    }

}