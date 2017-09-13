package com.example.gurtek.weather_api_ralem.di.components;

import com.example.gurtek.weather_api_ralem.activites.HomeActivity;
import com.example.gurtek.weather_api_ralem.di.modules.HomeActivityModule;
import com.example.gurtek.weather_api_ralem.di.scops.HomeActivityScop;

import dagger.Component;

/**
 * Created by Gurtek Singh on 6/13/2017.
 * Sachtech Solution
 * gurtekjattx@gmail.com
 */
@HomeActivityScop
@Component(modules = {HomeActivityModule.class},dependencies = AppComponent.class)
public interface HomeActivityComponent {
    void inject(HomeActivity homeActivity);
}
