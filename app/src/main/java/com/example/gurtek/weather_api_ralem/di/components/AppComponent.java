package com.example.gurtek.weather_api_ralem.di.components;

import com.example.gurtek.weather_api_ralem.di.modules.AppModule;
import com.example.gurtek.weather_api_ralem.di.modules.RetrofitModule;
import com.example.gurtek.weather_api_ralem.di.scops.ApplicationScop;
import com.example.gurtek.weather_api_ralem.retrofitcalls.WeatherApi;

import dagger.Component;

/**
 * Created by Gurtek Singh on 6/13/2017.
 * Sachtech Solution
 * gurtekjattx@gmail.com
 */
@ApplicationScop
@Component(modules = {AppModule.class, RetrofitModule.class})
public interface AppComponent {

    WeatherApi getApi();
}
