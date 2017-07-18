package com.example.gurtek.weather_api_ralem.models;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Gurtek Singh on 6/13/2017.
 * Sachtech Solution
 * gurtekjattx@gmail.com
 */
public class WeatherRealm  extends RealmObject{

    @PrimaryKey
    int id;

    double tempMax;
    double tempMin;
    int humidity;
    String cityName;

    public void notifyNewData(WeatherResponse response) {
        tempMax= response.list.get(0).main.temp_max;
        tempMin= response.list.get(0).main.temp_min;
        humidity=  response.list.get(0).main.humidity;
        cityName=response.city.name;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getTempMax() {
        return tempMax;
    }

    public void setTempMax(Double tempMax) {
        this.tempMax = tempMax;
    }

    public Double getTempMin() {
        return tempMin;
    }

    public void setTempMin(Double tempMin) {
        this.tempMin = tempMin;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
