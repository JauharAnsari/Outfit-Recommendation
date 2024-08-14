package com.example.outfit_recommendation

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface APIinterface  {
    @GET("weather")
    fun getAllMyData(
        @Query("q") city : String ,
        @Query("appid") appid : String ,
        @Query("units") units : String
    ) : Call<myWeatherData>

}