package com.example.gurtek.weather_api_ralem;

import android.app.Application;

import com.example.gurtek.weather_api_ralem.di.components.AppComponent;
import com.example.gurtek.weather_api_ralem.di.components.DaggerAppComponent;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by Gurtek Singh on 6/13/2017.
 * Sachtech Solution
 * gurtekjattx@gmail.com
 */
public class WeatherApp extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder().build();

        Realm.init(this);

        RealmConfiguration realmConfig =new RealmConfiguration.Builder().build();
        Realm.setDefaultConfiguration(realmConfig);


    }

    public AppComponent getAppComponent(){
        return appComponent;
    }
}
