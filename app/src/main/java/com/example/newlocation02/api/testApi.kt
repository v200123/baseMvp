package com.example.newlocation02.api

import com.example.newlocation02.bean.Weather
import io.reactivex.rxjava3.core.Flowable
import retrofit2.http.GET
import retrofit2.http.Path

interface testApi {
    @GET("{location}/weather.json")
    suspend fun getWeather(@Path("location") locatioin:String): Weather

}