package com.example.gurtek.weather_api_ralem.models;

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
    double currentTemp;
    private String description;
    private String state;
    private String country;

    private long timeStamp;

    /**
     *
     * Realm
     * */
    public WeatherRealm() {
    }

    public WeatherRealm(ForecastWeather.List data) {

        id=data.dt;
        tempMax=data.main.temp_max;
        tempMin=data.main.temp_min;
        humidity=data.main.humidity;
        currentTemp=data.main.temp;
        timeStamp=data.dt;

    }


    public void notifyNewData(WeatherResponse response) {
        tempMax= response.list.get(0).main.temp_max;
        tempMin= response.list.get(0).main.temp_min;
        humidity=  response.list.get(0).main.humidity;
        currentTemp=response.list.get(0).main.temp;
        description=response.list.get(0).weather.get(0).description;
        country=response.city.country;
        state =response.city.name;
    }

    public void notifyNewData(WeatherLocation response) {
        tempMax= response.getMain().getTempMax();
        tempMin= response.getMain().getTempMin();
        humidity=  response.getMain().getHumidity();
        currentTemp=response.getMain().getTemp();
        description=response.getWeather().get(0).getMain();
        country=response.getSys().getCountry();
        state =response.getName();
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

    public int getCurrentTemp() {
        return (int) Math.round(currentTemp);
    }

    public void setCurrentTemp(double currentTemp) {
        this.currentTemp = currentTemp;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
