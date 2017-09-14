package com.example.gurtek.weather_api_ralem.models;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * * Created by Gurtek Singh on 9/14/2017.
 * Sachtech Solution
 * gurtekjattx@gmail.com .
 */

public class WeatherForecastList extends RealmObject {

    @PrimaryKey
    int id;

    RealmList<WeatherRealm> forecastList;




    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public RealmList<WeatherRealm> getForecastList() {
        return forecastList;
    }

    public void setForecastList(RealmList<WeatherRealm> forecastList) {
        this.forecastList = forecastList;
    }
}
