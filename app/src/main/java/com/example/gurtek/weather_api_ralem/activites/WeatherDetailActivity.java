package com.example.gurtek.weather_api_ralem.activites;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.gurtek.weather_api_ralem.BaseActivity;
import com.example.gurtek.weather_api_ralem.R;
import com.example.gurtek.weather_api_ralem.di.components.DaggerWeatherActivityComponent;
import com.example.gurtek.weather_api_ralem.di.components.WeatherActivityComponent;
import com.example.gurtek.weather_api_ralem.di.modules.WeatherActivityModule;
import com.example.gurtek.weather_api_ralem.interfaces.weatherDListener.WeatherDetail;
import com.example.gurtek.weather_api_ralem.models.WeatherRealm;
import com.example.gurtek.weather_api_ralem.presenter.WeatherDetailPresenter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * * Created by Gurtek Singh on 9/12/2017.
 * Sachtech Solution
 * gurtekjattx@gmail.com .
 */

public class WeatherDetailActivity extends BaseActivity implements WeatherDetail.WeatherDetailView {

    RecyclerView recyclerView;
    @BindView(R.id.state_name)
    TextView stateName;
    @BindView(R.id.country_name)
    TextView country_name;
    @BindView(R.id.temp_now)
    TextView tempNow;

    @BindView(R.id.weathertiminglist)
    RecyclerView weathertiminglist;
    @BindView(R.id.description)
    TextView description;
    private WeatherActivityComponent activityComponent;

    @Inject
    WeatherDetailPresenter presenter;

    double LAT = 30.719246 ;
    double LON = 76.700525;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDagger();
        setContentView(R.layout.activity_weather_detail);
        ButterKnife.bind(this);

        presenter.getCurrentWeather(LAT, LON);


    }

    public void initDagger() {

        activityComponent = DaggerWeatherActivityComponent
                .builder()
                .weatherActivityModule(new WeatherActivityModule(this))
                .appComponent(getComponent())
                .build();

        activityComponent.inject(this);

    }

    @Override
    public void onError(String message) {

        showAlertDialog(message);

    }

    @Override
    public void onCurrentWeatherRecived(WeatherRealm weatherRealm) {

        setCurrentWeatherData(weatherRealm);

    }

    private void setCurrentWeatherData(WeatherRealm weatherRealm) {

        tempNow.setText(String.valueOf(weatherRealm.getCurrentTemp()));
        description.setText(weatherRealm.getDescription());
        stateName.setText(weatherRealm.getState());
        country_name.setText(weatherRealm.getCountry());




    }

    @Override
    public void onForeCastWeatherRecived() {

    }
}
