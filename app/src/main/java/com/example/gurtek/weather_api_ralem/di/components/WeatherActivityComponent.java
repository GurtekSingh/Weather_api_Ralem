package com.example.gurtek.weather_api_ralem.di.components;

import com.example.gurtek.weather_api_ralem.activites.WeatherDetailActivity;
import com.example.gurtek.weather_api_ralem.di.modules.WeatherActivityModule;
import com.example.gurtek.weather_api_ralem.di.scops.WeatherDetailScops;

import dagger.Component;

/**
 * Created by Gurtek Singh on 6/13/2017.
 * Sachtech Solution
 * gurtekjattx@gmail.com
 */
@WeatherDetailScops
@Component(modules = {WeatherActivityModule.class},dependencies = AppComponent.class)
public interface WeatherActivityComponent {

    void inject(WeatherDetailActivity weatherDetailActivity);
}
