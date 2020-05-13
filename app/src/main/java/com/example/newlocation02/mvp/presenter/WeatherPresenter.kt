package com.example.newlocation02.mvp.presenter

import com.example.newlocation02.mvp.IWeatherView
import com.lc.basemvp.BasePresenter
import com.lc.basemvp.out
import com.lc.newlocation.ServiceCreate
import io.reactivex.rxjava3.disposables.Disposable
import kotlinx.coroutines.*

class WeatherPresenter : BasePresenter<IWeatherView>(),CoroutineScope by MainScope() {
    fun getWeatherBean(){
        launch {
            ServiceCreate.getRetrofitBuild().getWeather("121.6544,25.1552")
        }

    }
}