package com.example.gurtek.weather_api_ralem.repos;


import com.example.gurtek.weather_api_ralem.interfaces.weatherDListener.WeatherDetail;
import com.example.gurtek.weather_api_ralem.models.ForecastWeather;
import com.example.gurtek.weather_api_ralem.models.WeatherLocation;
import com.example.gurtek.weather_api_ralem.retrofitcalls.WeatherApi;

import io.reactivex.Observable;

/**
 * * Created by Gurtek Singh on 9/13/2017.
 * Sachtech Solution
 * gurtekjattx@gmail.com .
 */

public class NetworkDataSource  implements WeatherDetail.NetworkRepository {

    private WeatherApi api;
    private String apiKey="a025c8b007d236dc195a53596f903202";

    public NetworkDataSource(WeatherApi api) {
        this.api = api;
    }

    @Override
    public Observable<WeatherLocation> getWeatherByLocation(double lat, double lon) {
        return api.getWeatherbyLocation(lat, lon,"metric", apiKey);
    }

    @Override
    public Observable<ForecastWeather> getForecaseWeather(double lat, double lon) {
        return api.getForecaseWeather(lat, lon,apiKey,"metric",6);
    }
}
