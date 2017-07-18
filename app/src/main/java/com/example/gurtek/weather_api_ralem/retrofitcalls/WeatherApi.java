package com.example.gurtek.weather_api_ralem.retrofitcalls;


import android.util.Log;

import com.example.gurtek.weather_api_ralem.models.WeatherResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Gurtek Singh on 6/13/2017.
 * Sachtech Solution
 * gurtekjattx@gmail.com
 */
public interface WeatherApi {

    @GET("forecast?")
    Observable<WeatherResponse> getWeather(
            @Query("id") String id,
            @Query("appid") String AppId

    );
}
