package com.lc.newlocation

import android.view.View
import android.widget.Button
import com.example.newlocation02.api.testApi
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
@packageName com.lc.newlocation
@author admin
@date 2020/3/10
 */


fun Button.click(a: (view: View) -> Unit) {
    this.setOnClickListener(a)
}

fun <T : Button> T.startCountDown() {

}

object ServiceCreate {
    fun getRetrofitBuild(): testApi {
        val addInterceptor = OkHttpClient.Builder().addNetworkInterceptor(HttpLoggingInterceptor().also {
            it.level = HttpLoggingInterceptor.Level.BODY
        })
            .addInterceptor(object : Interceptor{
            override fun intercept(chain: Interceptor.Chain): Response {
                val request: Request = chain.request()
                val build = request.newBuilder()
                    .url(request.url)
                    .addHeader("User-Agent", "ddeegggfff")
                    .addHeader("map","redfsdfsdf")
                    .build()
                return chain.proceed(build)
            }
        })
        return Retrofit.Builder().baseUrl("https://api.caiyunapp.com/v2/6VI4vN5H0a8mQWlL/")
        .addConverterFactory(GsonConverterFactory.create())
            .callFactory(addInterceptor.build())
            .build().create(testApi::class.java)}
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

object TimeUtil {
    @JvmStatic
    fun startCount(button: Button) {

    }
}






