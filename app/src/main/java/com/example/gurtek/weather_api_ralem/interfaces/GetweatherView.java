package com.example.gurtek.weather_api_ralem.interfaces;

import com.example.gurtek.weather_api_ralem.errors.ErrorMode;
import com.example.gurtek.weather_api_ralem.models.WeatherRealm;

/**
 * Created by Gurtek Singh on 6/13/2017.
 * Sachtech Solution
 * gurtekjattx@gmail.com
 */
public interface GetweatherView extends BaseListener {

   void onWeatherDataRecive(WeatherRealm weatherRealm);
   void onError( String e);

}
