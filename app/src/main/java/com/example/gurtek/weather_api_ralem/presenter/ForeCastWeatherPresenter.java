package com.example.gurtek.weather_api_ralem.presenter;

import com.example.gurtek.weather_api_ralem.interfaces.DataBaseRepo;
import com.example.gurtek.weather_api_ralem.interfaces.weatherDListener.WeatherDetail;
import com.example.gurtek.weather_api_ralem.models.ForecastWeather;
import com.example.gurtek.weather_api_ralem.models.WeatherRealm;

import java.util.List;

import io.reactivex.functions.Function;

/**
 * * Created by Gurtek Singh on 9/14/2017.
 * Sachtech Solution
 * gurtekjattx@gmail.com .
 */

public class ForeCastWeatherPresenter extends BasePresenter {


    private WeatherDetail.WeatherDetailView view;
    private WeatherDetail.NetworkRepository remoteRepo;
    private DataBaseRepo localRepo;

    /**
     * @see WeatherDetailPresenter for usage
     * detail
     */
    public ForeCastWeatherPresenter() {
        super(true);
    }

    public ForeCastWeatherPresenter(WeatherDetail.WeatherDetailView view,
                                    WeatherDetail.NetworkRepository remoteRepo,
                                    DataBaseRepo localRepo) {
        super(false);
        this.view = view;
        this.remoteRepo = remoteRepo;
        this.localRepo = localRepo;
    }

    public void getForeCastWeather(double lat, double lon) {

        remoteRepo.getForecaseWeather(lat, lon)
                .compose(applyNtoCSchedulers())
                .map(new Function<ForecastWeather, List<WeatherRealm>>() {
                    @Override
                    public List<WeatherRealm> apply(ForecastWeather forecastWeather) throws Exception {
                        return localRepo.createRalmList(forecastWeather);
                    }
                }).subscribe();

    }


}
