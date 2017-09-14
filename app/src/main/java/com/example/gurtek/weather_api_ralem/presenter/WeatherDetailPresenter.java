package com.example.gurtek.weather_api_ralem.presenter;

import com.example.gurtek.weather_api_ralem.interfaces.DataBaseRepo;
import com.example.gurtek.weather_api_ralem.interfaces.weatherDListener.WeatherDetail;
import com.example.gurtek.weather_api_ralem.models.WeatherLocation;
import com.example.gurtek.weather_api_ralem.models.WeatherRealm;

import io.reactivex.functions.Function;

/**
 * * Created by Gurtek Singh on 9/13/2017.
 * Sachtech Solution
 * gurtekjattx@gmail.com .
 */

public class WeatherDetailPresenter extends BasePresenter {


    private WeatherDetail.WeatherDetailView view;
    private WeatherDetail.NetworkRepository remoteRepo;
    private DataBaseRepo localRepo;

    /**
     * this default constructor
    * is used during unit test
    * so don't remove this
    * @see WeatherDetailPresenter testcase
    * */
    public WeatherDetailPresenter() {
        super(true);
    }

    public WeatherDetailPresenter(WeatherDetail.WeatherDetailView view,
                                  WeatherDetail.NetworkRepository remoteRepo,
                                  DataBaseRepo localRepo,
                                  boolean isTesting) {
        super(isTesting);
        this.view = view;
        this.remoteRepo = remoteRepo;
        this.localRepo = localRepo;
    }


    public void getCurrentWeather(double lat, double lon) {

        remoteRepo.getWeatherByLocation(lat, lon)
                .compose(applyNtoCSchedulers())
                .doOnSubscribe(disposable -> displayPreviewData())
                .map(weatherLocation -> localRepo.writeTodataBase(weatherLocation))
                .observeOn(onMain())
                .map(id -> localRepo.readfromDataBase(id))
                .subscribe(weatherRealm -> view.onCurrentWeatherRecived(weatherRealm),
                        throwable -> view.onError(throwable.getMessage()));

    }

    private void displayPreviewData() {

        WeatherRealm weatherRealm = localRepo.readfromDataBase();
        if (weatherRealm != null) {
            view.onCurrentWeatherRecived(weatherRealm);
        }

    }



}
