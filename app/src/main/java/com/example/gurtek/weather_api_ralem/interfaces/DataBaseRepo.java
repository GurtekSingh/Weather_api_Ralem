package com.example.gurtek.weather_api_ralem.interfaces;

import com.example.gurtek.weather_api_ralem.models.WeatherRealm;
import com.example.gurtek.weather_api_ralem.models.WeatherResponse;

/**
 * Created by Gurtek Singh on 6/13/2017.
 * Sachtech Solution
 * gurtekjattx@gmail.com
 */
public interface DataBaseRepo {

    int writeTodataBase(WeatherResponse response);
    WeatherRealm readfromDataBase(int response);


}
