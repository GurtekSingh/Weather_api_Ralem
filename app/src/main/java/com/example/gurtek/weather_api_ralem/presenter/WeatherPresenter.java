package com.example.gurtek.weather_api_ralem.presenter;

import com.example.gurtek.weather_api_ralem.injection.Injection;
import com.example.gurtek.weather_api_ralem.interfaces.DataBaseRepo;
import com.example.gurtek.weather_api_ralem.interfaces.GetweatherView;
import com.example.gurtek.weather_api_ralem.interfaces.WeatherImp;
import com.example.gurtek.weather_api_ralem.models.WeatherLocation;
import com.example.gurtek.weather_api_ralem.models.WeatherRealm;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Gurtek Singh on 6/13/2017.
 * Sachtech Solution
 * gurtekjattx@gmail.com
 */
public class WeatherPresenter extends BasePresenter implements WeatherImp {


    private GetweatherView getweatherView;

    private DataBaseRepo repo;

    private Disposable subscribe;


    public WeatherPresenter(GetweatherView getweatherView, DataBaseRepo repo,Boolean onTesting) {
        super(onTesting);
        this.getweatherView = getweatherView;
        this.repo = repo;
    }

    @Override
    public void getData(Integer idd) {

        Observable<WeatherRealm> map = repo.getWeather(String.valueOf(idd))
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



    public void getWeatherInfo(String lat, String lon) {

        checkDataInDatabase(lat, lon);

        repo.getWeatherByLocation(lat, lon)
                .subscribeOn(onIo())
                .observeOn(onComputation())
                .map(new Function<WeatherLocation, Integer>() {
                    @Override
                    public Integer apply(WeatherLocation weatherResponse) throws Exception {
                        return repo.writeTodataBase(weatherResponse);
                    }
                })
                .observeOn(onMain())
                .map(new Function<Integer, WeatherRealm>() {
                    @Override
                    public WeatherRealm apply(Integer cityId) throws Exception {
                        return repo.readfromDataBase(cityId);
                    }
                })
                .subscribe(weatherRealm -> getweatherView.onWeatherDataRecive(weatherRealm)
                       ,throwable -> getweatherView.onError(throwable.getMessage()));

    }

    private void checkDataInDatabase(String lat, String lon) {

        int id = Integer.parseInt((int)(Double.parseDouble(lat)) + "" + (int)(Double.parseDouble(lon)));

        WeatherRealm weatherRealm = repo.readfromDataBase(id);
        if (weatherRealm != null)
            getweatherView.onWeatherDataRecive(weatherRealm);

    }


    public void unSubscribe() {

        if (subscribe != null && subscribe.isDisposed()) subscribe.dispose();

    }


}
