package com.example.gurtek.weather_api_ralem.di.modules;

import com.example.gurtek.weather_api_ralem.activites.WeatherDetailActivity;
import com.example.gurtek.weather_api_ralem.di.scops.WeatherDetailScops;
import com.example.gurtek.weather_api_ralem.interfaces.DataBaseRepo;
import com.example.gurtek.weather_api_ralem.interfaces.weatherDListener.WeatherDetail;
import com.example.gurtek.weather_api_ralem.presenter.WeatherDetailPresenter;
import com.example.gurtek.weather_api_ralem.repos.DataBaseRepository;
import com.example.gurtek.weather_api_ralem.repos.NetworkDataSource;
import com.example.gurtek.weather_api_ralem.retrofitcalls.WeatherApi;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Gurtek Singh on 6/13/2017.
 * Sachtech Solution
 * gurtekjattx@gmail.com
 */
@Module
public class WeatherActivityModule {

    private WeatherDetailActivity activity;

    public WeatherActivityModule(WeatherDetailActivity activity) {
        this.activity = activity;
    }

    @Provides
    @WeatherDetailScops
    WeatherDetail.WeatherDetailView provideWeatherView(){
        return activity;
    }

    @Provides
    @WeatherDetailScops
    WeatherDetail.NetworkRepository provideRemoteRepo(WeatherApi api){
        return new NetworkDataSource(api);
    }

    @Provides
    @WeatherDetailScops
    WeatherDetailPresenter provideWeatherPresenter(WeatherDetail.WeatherDetailView view,
                                             WeatherDetail.NetworkRepository remoteRepo,
                                             DataBaseRepo localRepo){
        return new WeatherDetailPresenter(view,remoteRepo,localRepo,false);
    }

    @Provides
    @WeatherDetailScops
    DataBaseRepo provideDataBase(WeatherApi api){
        return new DataBaseRepository(api);
    }



}
