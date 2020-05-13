package com.lc.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.core.content.FileProvider
import java.io.File

class LcUtils {

    companion object{
        lateinit var  context:Context
        var SPName:String = "com.lcUtils.SharedPreferences"
        fun init(context: Context){
            this.context = context
        }

        /**
         * 转换 Uri 路径，针对 7.0 之后 File 的适配
         */
        @JvmStatic
        fun getUriByFile(context: Context, file: File, authority: String): Uri {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                return FileProvider.getUriForFile(context, authority, file)
            }
            return Uri.fromFile(file)
        }


       inline fun <reified  T > startActivite(context: Context,block:Intent.()-> Unit){
            context.startActivity(Intent(context,T::class.java).apply(block))

       }
    }
}