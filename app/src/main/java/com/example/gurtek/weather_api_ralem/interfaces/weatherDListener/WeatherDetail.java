package com.example.gurtek.weather_api_ralem.interfaces.weatherDListener;

import com.example.gurtek.weather_api_ralem.models.ForecastWeather;
import com.example.gurtek.weather_api_ralem.models.WeatherLocation;
import com.example.gurtek.weather_api_ralem.models.WeatherRealm;

import io.reactivex.Observable;

/**
 * * Created by Gurtek Singh on 9/13/2017.
 * Sachtech Solution
 * gurtekjattx@gmail.com .
 */

public interface WeatherDetail {

     interface WeatherDetailView{

        void onError(String message);
        void onCurrentWeatherRecived(WeatherRealm weatherRealm);
         void onForeCastWeatherRecived();
     }


     interface NetworkRepository  {
        Observable<WeatherLocation> getWeatherByLocation(double lat, double lon);

         Observable<ForecastWeather> getForecaseWeather(double lat, double lon);
     }

}
