package com.lc.newlocation

import android.view.View
import android.widget.Button
import com.example.newlocation02.view.CountDownButton
import com.lc.basemvp.out
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

/**
@packageName com.lc.newlocation
@author admin
@date 2020/3/10
 */


fun Button.click(a: (view: View) -> Unit){
    this.setOnClickListener(a)
}

fun < T:Button > T.startCountDown(){

}

//object Help{
//   class CountHelp(private var countDownButton: CountDownButton, var time:Long){
//        fun start(){
//            Observable.interval(0,1000, TimeUnit.MILLISECONDS).take(time+1).subscribeOn(Schedulers.single())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe {
//                    "我收到了RxJava的消息".out()
//                    "当前这个控件的class${this}".out()
//                    countDownButton.text = "剩余时间为${time--}"
//                }
//        }
//    }
//}

object TimeUtil{
    @JvmStatic
    fun startCount(button: Button){

    }
}






