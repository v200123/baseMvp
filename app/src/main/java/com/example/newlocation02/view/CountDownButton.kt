package com.example.newlocation02.view

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.appcompat.widget.AppCompatButton
import com.example.newlocation02.R
import com.lc.basemvp.out
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableSource
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.functions.Action
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.*
import java.util.concurrent.TimeUnit

class CountDownButton @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = R.attr.buttonStyle
//    totalTime:Long,
//    endText:String
) : AppCompatButton(context, attrs, defStyleAttr) {
    private var enableCount = true
    private var time = 10L

    private var countHelp = CountHelp
    init {
        countHelp.countDownButton = this
    }

    fun start() {
        when(getState()){
            CountState.Running->{    countHelp.Pause()}
            CountState.PAUSE->{   countHelp.start()}
            else->{   countHelp.time = time
                countHelp.start()}
        }


//        if (getState() != CountState.Running) {
//            countHelp.time = time
//            countHelp.start()
//        }else{
//            countHelp.Pause()
//        }
    }



    fun getState(): CountState = countHelp.state
}


object CountHelp {
    private val  mRxJavaList: CompositeDisposable = CompositeDisposable()
    var state: CountState = CountState.WAIT
    var countDownButton: CountDownButton? = null
        set(value) {
            field = value
            field?.text = "剩余时间为${time--}"
            field!!.isEnabled = time <= 0
        }

    @Volatile
    var time: Long = 0
    fun start() {

        val subscribe = Observable.interval(if(state==CountState.PAUSE)1000L else 0L, 1000, TimeUnit.MILLISECONDS).take(time + 1)
            .subscribeOn(Schedulers.single())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    "当前输出了么".out()
                    countDownButton?.text = "${time--}秒后"
                }, { "出错了".out() }, {
                    "完成了".out()
                    countDownButton?.text = "重新发送"
                    state = CountState.STOPPING
                }
            )
        state = CountState.Running
        mRxJavaList.add(subscribe)
    }
    fun Pause(){
        mRxJavaList.clear()
        state = CountState.PAUSE
    }


}


enum class CountState {
    Running, STOPPING, PAUSE, WAIT

}