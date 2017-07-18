package com.example.gurtek.weather_api_ralem.repos;

import android.util.Log;

import com.example.gurtek.weather_api_ralem.interfaces.DataBaseRepo;
import com.example.gurtek.weather_api_ralem.models.WeatherRealm;
import com.example.gurtek.weather_api_ralem.models.WeatherResponse;

import io.realm.Realm;

/**
 * Created by Gurtek Singh on 6/13/2017.
 * Sachtech Solution
 * gurtekjattx@gmail.com
 */
public class DataBaseRepository implements DataBaseRepo {


    public DataBaseRepository() {

    }

    @Override
    public int writeTodataBase(WeatherResponse response) {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(transactionRealm -> {

            Log.e("Threading","Writing to DataBase"+Thread.currentThread().getId());

            WeatherRealm weatherRealm = DataBaseRepository.this.findInRealm(transactionRealm, response.city.id);

            if (weatherRealm == null)
                weatherRealm = transactionRealm.createObject(WeatherRealm.class, response.city.id);
                 weatherRealm.notifyNewData(response);

        });
        realm.close();
        return response.city.id;
    }

    @Override
    public WeatherRealm readfromDataBase(int id) {
        Realm realm = Realm.getDefaultInstance();
        return findInRealm(realm, id);
    }

    private WeatherRealm findInRealm(Realm realm, int id) {
        return realm.where(WeatherRealm.class).equalTo("id", id).findFirst();
    }
}
