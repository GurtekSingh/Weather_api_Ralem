package com.example.gurtek.weather_api_ralem.injection;

import com.example.gurtek.weather_api_ralem.models.Coord;
import com.example.gurtek.weather_api_ralem.models.WeatherLocation;
import com.example.gurtek.weather_api_ralem.models.WeatherRealm;
import com.example.gurtek.weather_api_ralem.models.WeatherResponse;

/**
 * * Created by Gurtek Singh on 9/9/2017.
 * Sachtech Solution
 * gurtekjattx@gmail.com .
 */

public class Injection {


    private static WeatherRealm weatherRealm;
    private static WeatherLocation weatherLocation;

    public static WeatherRealm provideFakeWeatherRalm() {
        if (weatherRealm == null) {
            weatherRealm = new WeatherRealm();
            weatherRealm.setId(1212);
        }

        return weatherRealm;
    }

    public static WeatherLocation provideLocationResponse() {
        if (weatherLocation == null) {
            weatherLocation = new WeatherLocation();

            WeatherLocation.Coord coord = new WeatherLocation.Coord();
            coord.setLat(12);
            coord.setLon(12);
            weatherLocation.setCoord(coord);

            WeatherLocation.Main main=new WeatherLocation.Main();
            main.setHumidity(50);
            main.setTempMax(60);
            main.setTempMin(40);

            weatherLocation.setMain(main);

            WeatherLocation.Sys sys=new WeatherLocation.Sys();
            sys.setCountry("IO");
            weatherLocation.setSys(sys);

        }

        return weatherLocation;
    }

}