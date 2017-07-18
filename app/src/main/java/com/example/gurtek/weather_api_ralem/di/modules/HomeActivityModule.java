package com.example.gurtek.weather_api_ralem.di.modules;

import android.content.Context;

import com.example.gurtek.weather_api_ralem.activites.HomeActivity;
import com.example.gurtek.weather_api_ralem.di.qualifiers.HomeActivityContext;
import com.example.gurtek.weather_api_ralem.di.scops.HomeActivityScop;
import com.example.gurtek.weather_api_ralem.interfaces.DataBaseRepo;
import com.example.gurtek.weather_api_ralem.interfaces.GetweatherView;
import com.example.gurtek.weather_api_ralem.interfaces.WeatherRepo;
import com.example.gurtek.weather_api_ralem.repos.DataBaseRepository;
import com.example.gurtek.weather_api_ralem.presenter.WeatherPresenter;
import com.example.gurtek.weather_api_ralem.retrofitcalls.WeatherApi;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;

/**
 * Created by Gurtek Singh on 6/13/2017.
 * Sachtech Solution
 * gurtekjattx@gmail.com
 */
@Module
public class HomeActivityModule {

    private final HomeActivity activity;

    public HomeActivityModule(HomeActivity activity) {
        this.activity = activity;
    }


    @Provides
    @HomeActivityContext
    @HomeActivityScop
    HomeActivity provideActivityContext() {
        return activity;
    }

    @Provides
    GetweatherView provideWeaterView(@HomeActivityContext HomeActivity context) {
        return context;
    }


    @Provides
    @HomeActivityScop
    WeatherPresenter provideRepository(WeatherApi api,DataBaseRepo dataBaseRepo,GetweatherView getweatherView) {
        return new WeatherPresenter(getweatherView,api,dataBaseRepo);
    }


    @Provides
    @HomeActivityScop
    DataBaseRepo provideDataBaseRepo(){
        return new DataBaseRepository();
    }

    @Provides
    @HomeActivityScop()
    Realm provideRealm(){
        return Realm.getDefaultInstance();
    }

}
