package com.example.newlocation02.interceptor

import com.lc.basemvp.out
import okhttp3.Interceptor
import okhttp3.Response

class MyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        chain.request().also {
            "当前是https请求么${it.isHttps}".out()
            "请求连接是${it.method}://${it.url}".out()
            it.url.apply {
                "host的值为：$host\npath的值为：$encodedPath\n".out()
                        "encodedQuery：$encodedQuery\n".out()
                        "enquPath的值是:$encodedFragment".out()
                        "fragment的值为:$fragment".out()
            }
        }
        return chain.proceed(chain.request())


    }
}