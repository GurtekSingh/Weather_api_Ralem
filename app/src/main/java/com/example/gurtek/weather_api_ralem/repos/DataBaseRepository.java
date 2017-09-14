package com.example.gurtek.weather_api_ralem.repos;

import android.util.Log;

import com.example.gurtek.weather_api_ralem.interfaces.DataBaseRepo;
import com.example.gurtek.weather_api_ralem.models.ForecastWeather;
import com.example.gurtek.weather_api_ralem.models.WeatherLocation;
import com.example.gurtek.weather_api_ralem.models.WeatherRealm;
import com.example.gurtek.weather_api_ralem.models.WeatherResponse;
import com.example.gurtek.weather_api_ralem.retrofitcalls.WeatherApi;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.realm.Realm;

/**
 * Created by Gurtek Singh on 6/13/2017.
 * Sachtech Solution
 * gurtekjattx@gmail.com
 */
public class DataBaseRepository implements DataBaseRepo {


    private WeatherApi api;

    String apiKey = "a025c8b007d236dc195a53596f903202";

    public DataBaseRepository(WeatherApi api) {

        this.api = api;
    }

    @Override
    public int writeTodataBase(WeatherResponse response) {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(transactionRealm -> {

            Log.e("Threading", "Writing to DataBase" + Thread.currentThread().getId());

            WeatherRealm weatherRealm = DataBaseRepository.this.findInRealm(transactionRealm, response.city.id);

            if (weatherRealm == null)
                weatherRealm = transactionRealm.createObject(WeatherRealm.class, response.city.id);
            weatherRealm.notifyNewData(response);

        });
        realm.close();
        return response.city.id;
    }

    @Override
    public int writeTodataBase(WeatherLocation response) {
        Realm realm = Realm.getDefaultInstance();
        int id = 1;
        realm.executeTransaction(transactionRealm -> {

            Log.e("Threading", "Writing to DataBase" + Thread.currentThread().getId());


            WeatherRealm weatherRealm = DataBaseRepository.this.findInRealm(transactionRealm,id);

            if (weatherRealm == null)
                weatherRealm = transactionRealm.createObject(WeatherRealm.class,id);
            weatherRealm.notifyNewData(response);

        });
        realm.close();
        return id;
    }


    @Override
    public WeatherRealm readfromDataBase(int id) {
        Realm realm = Realm.getDefaultInstance();
        return findInRealm(realm, id);
    }

    @Override
    public WeatherRealm readfromDataBase() {
        Realm realm = Realm.getDefaultInstance();
        return findInRealm(realm,1);
    }


    public Observable<WeatherResponse> getWeather(String s) {
        return api.getWeather(s, apiKey);
    }

    @Override
    public Observable<WeatherLocation> getWeatherByLocation(double lat, double lon) {
        return api.getWeatherbyLocation(lat, lon,"metric", apiKey);
    }

    @Override
    public Observable<WeatherLocation> getWeatherByLocation(String lat, String lon) {
        return getWeatherByLocation(Double.parseDouble(lat), Double.parseDouble(lon));
    }

    @Override
    public List<WeatherRealm> createRalmList(ForecastWeather weatherresponse) {

        List<WeatherRealm> realmList=new ArrayList<>();

        for (ForecastWeather.List data: weatherresponse.list) {
            WeatherRealm realm=new WeatherRealm(data);
            realmList.add(realm);
        }

        return realmList;

    }

    @Override
    public List<WeatherRealm> writeTodataBase(ForecastWeather forecastWeather) {
        return null;
    }


    private WeatherRealm findInRealm(Realm realm, int id) {
        return realm.where(WeatherRealm.class).equalTo("id", id).findFirst();
    }

    private WeatherRealm findInRealm(Realm realm) {
        return realm.where(WeatherRealm.class).findFirst();
    }
}
