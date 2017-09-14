package com.example.gurtek.weather_api_ralem.interfaces;

import com.example.gurtek.weather_api_ralem.models.ForecastWeather;
import com.example.gurtek.weather_api_ralem.models.WeatherLocation;
import com.example.gurtek.weather_api_ralem.models.WeatherRealm;
import com.example.gurtek.weather_api_ralem.models.WeatherResponse;

import java.util.List;

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

    WeatherRealm readfromDataBase();

    Observable<WeatherResponse> getWeather(String s);

    Observable<WeatherLocation> getWeatherByLocation(double lat, double lon);

    Observable<WeatherLocation> getWeatherByLocation(String lat, String lon);

    List<WeatherRealm> createRalmList(ForecastWeather weatherresponse);

    List<WeatherRealm> writeTodataBase(ForecastWeather forecastWeather);
}
