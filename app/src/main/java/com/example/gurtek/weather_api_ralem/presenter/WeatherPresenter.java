package com.example.gurtek.weather_api_ralem.presenter;

import android.util.Log;

import com.example.gurtek.weather_api_ralem.interfaces.DataBaseRepo;
import com.example.gurtek.weather_api_ralem.interfaces.GetweatherView;
import com.example.gurtek.weather_api_ralem.interfaces.WeatherRepo;
import com.example.gurtek.weather_api_ralem.models.WeatherRealm;
import com.example.gurtek.weather_api_ralem.retrofitcalls.WeatherApi;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Gurtek Singh on 6/13/2017.
 * Sachtech Solution
 * gurtekjattx@gmail.com
 */
public class WeatherPresenter implements WeatherRepo {


    private GetweatherView getweatherView;
    private WeatherApi api;
    private DataBaseRepo repo;
    String apiKey ="a025c8b007d236dc195a53596f903202";
    private Disposable subscribe;


    public WeatherPresenter(GetweatherView getweatherView, WeatherApi api, DataBaseRepo repo) {
        this.getweatherView = getweatherView;

        this.api = api;
        this.repo = repo;
    }

    @Override
    public void getData(Integer idd) {

                Observable<WeatherRealm> map = api.getWeather(String.valueOf(idd), apiKey)
                        .subscribeOn(Schedulers.io())
                        .observeOn(Schedulers.computation())
                        .map(weatherResponse -> repo.writeTodataBase(weatherResponse))
                        .observeOn(AndroidSchedulers.mainThread())
                        .map(id -> repo.readfromDataBase(id))
                        .onErrorResumeNext(throwable -> {
                            return Observable.just(repo.readfromDataBase(idd));
                        });

                // Read any cached results
                WeatherRealm cachedWeather = repo.readfromDataBase(idd);

                if (cachedWeather != null) {
                    map.mergeWith(Observable.just(cachedWeather));
                }

                subscribe = map.subscribe(weatherRealm -> {
                    try {
                        getweatherView.onWeatherDataRecive(weatherRealm);
                    } catch (Exception e) {
                        getweatherView.onError(e.getMessage());
                    }

                }, throwable -> {
                    getweatherView.onError(throwable.getMessage());
                });


            }



    public void unSubscribe(){

        if(subscribe!=null&&subscribe.isDisposed()) subscribe.dispose();

    }

}
