package com.example.newlocation02.fragment

import com.example.newlocation02.R
import com.example.newlocation02.bean.Weather
import com.example.newlocation02.mvp.IWeatherView
import com.example.newlocation02.mvp.presenter.WeatherPresenter
import com.lc.basemvp.BaseFragment
import com.lc.basemvp.out
import kotlinx.android.synthetic.main.fragment_weather.*
import kotlin.concurrent.thread


class WeatherFragment : BaseFragment<IWeatherView, WeatherPresenter>(),IWeatherView {
    override fun createPresenter(): WeatherPresenter = WeatherPresenter()

    override fun getLayoutId(): Int = R.layout.fragment_weather
    override fun showString(weather: Weather) {
        weather.toString().out()
    }

    override fun initView() {
//        GPV.updateProcess( .5f)
        GPV.updateProcess( 1f)
    }

    override fun initData() {
//        mPresenter.getWeatherBean()
//        val smsContentObserver = SMSContentObserver(Handler(Looper.getMainLooper()), mContext)
//        mContext.contentResolver.registerContentObserver(
//            Uri.parse("content://sms/"), true, smsContentObserver)
    }

    override fun showMsg(msg: String) {
    }
}

//
//class SMSContentObserver(handler: Handler,val mContext: Context) : ContentObserver(handler) {
//    private lateinit var code:String
//    override fun onChange(selfChange: Boolean, uri: Uri?) {
//        super.onChange(selfChange, uri)
//        Logger.e(uri.toString())
//        if(uri.toString() == "content://sms/raw/")
//        {return}
//        val inboxUri = Uri.parse("content://sms/inbox")
//
//        // 按时间顺序排序短信数据库
//
//        // 按时间顺序排序短信数据库
//        val c: Cursor? = mContext.getContentResolver().query(
//            inboxUri, null, null,
//            null, "date desc"
//        )
//        if (c != null) {
//            if (c.moveToFirst()) {
//                // 获取短信提供商的手机号
//                val address: String = c.getString(c.getColumnIndex("address"))
//                // 获取短信内容
//                val body: String = c.getString(c.getColumnIndex("body"))
//                Logger.i("tag", "body------->$body")
//                // 判断手机号是否为目标号码(短信提供商的号码)
//                // 在这里我们的短信提供商的号码如果是固定的话.我们可以再加一个判断,这样就不会受到别的短信应用的验证码的影响了
//                // 不然的话就在我们的正则表达式中,加一些自己的判断,例如短信中含有自己应用名字啊什么的...
//                /*if (!address.equals("13342290623"))
//                {
//                    Log.i("tag","------->没有读取到内容");
//                    return;
//                }*/
//                // 正则表达式截取短信中的6位验证码
//                val pattern: Pattern = Pattern.compile("(\\d{6})")
//                val matcher: Matcher = pattern.matcher(body)
//
//                // 利用handler将得到的验证码发送给主线程
//                if (matcher.find()) {
//                    code = matcher.group(0)
//                    Logger.d("当前的验证码是${code}")
//                }
//            }
//            c.close()
//        }
//    }
//}