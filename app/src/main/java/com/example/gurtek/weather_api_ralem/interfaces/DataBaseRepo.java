package com.example.gurtek.weather_api_ralem.interfaces;

import com.example.gurtek.weather_api_ralem.models.WeatherLocation;
import com.example.gurtek.weather_api_ralem.models.WeatherRealm;
import com.example.gurtek.weather_api_ralem.models.WeatherResponse;

import io.reactivex.Observable;

/**
 * Created by Gurtek Singh on 6/13/2017.
 * Sachtech Solution
 * gurtekjattx@gmail.com
 */
public interface DataBaseRepo {

    int writeTodataBase(WeatherResponse response);

    int writeTodataBase(WeatherLocation response);

    WeatherRealm readfromDataBase(int response);


    Observable<WeatherResponse> getWeather(String s);

    Observable<WeatherLocation> getWeatherByLocation(String lat, String lon);

}
