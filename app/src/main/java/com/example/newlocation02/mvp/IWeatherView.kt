package com.example.newlocation02.mvp

import com.example.newlocation02.bean.Weather
import com.lc.basemvp.IBaseFragmentView

interface IWeatherView : IBaseFragmentView {
    fun showString(weather: Weather)
}