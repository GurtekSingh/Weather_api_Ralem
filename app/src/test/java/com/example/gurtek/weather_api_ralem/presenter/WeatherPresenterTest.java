package com.example.gurtek.weather_api_ralem.presenter;

import com.example.gurtek.weather_api_ralem.injection.Injection;
import com.example.gurtek.weather_api_ralem.interfaces.DataBaseRepo;
import com.example.gurtek.weather_api_ralem.interfaces.GetweatherView;
import com.example.gurtek.weather_api_ralem.models.WeatherLocation;
import com.example.gurtek.weather_api_ralem.models.WeatherRealm;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import io.reactivex.Observable;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * * Created by Gurtek Singh on 9/9/2017.
 * Sachtech Solution
 * gurtekjattx@gmail.com .
 */
@RunWith(MockitoJUnitRunner.class)
public class WeatherPresenterTest {


    public static final String LAT = "12";
    public static final String LON = "12";
    WeatherPresenter presenter;

    @Mock
    GetweatherView view;

    @Mock
    DataBaseRepo repo;

    WeatherLocation response;


    WeatherRealm weatherRealm;

    @Before
    public void setUp() {

        presenter = new WeatherPresenter(view, repo,true);
        response= Injection.provideLocationResponse();

        weatherRealm=Injection.provideFakeWeatherRalm();
    }

    @Test
    public void shouldgetWeatherData() {

        when(repo.getWeatherByLocation(LAT, LON))
                .thenReturn(Observable.just(response));

        when(repo.writeTodataBase(response)).thenReturn(5);

        when(repo.readfromDataBase(5)).thenReturn(weatherRealm);

        presenter.getWeatherInfo(LAT,LON);

        verify(view).onWeatherDataRecive(weatherRealm);

    }



}