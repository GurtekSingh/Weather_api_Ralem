package com.example.gurtek.weather_api_ralem.presenter;

import com.example.gurtek.weather_api_ralem.injection.Injection;
import com.example.gurtek.weather_api_ralem.interfaces.DataBaseRepo;
import com.example.gurtek.weather_api_ralem.interfaces.weatherDListener.WeatherDetail;
import com.example.gurtek.weather_api_ralem.models.ForecastWeather;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import io.reactivex.Observable;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * * Created by Gurtek Singh on 9/14/2017.
 * Sachtech Solution
 * gurtekjattx@gmail.com .
 */
public class ForeCastWeatherPresenterTest {



    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    WeatherDetail.NetworkRepository remoteRepo;

    @Mock
    DataBaseRepo localRepo;

    @Mock
    WeatherDetail.WeatherDetailView view;

    private double LAT=12;
    private double LON=12;


    @InjectMocks ForeCastWeatherPresenter presenter;

    ForecastWeather weatherresponse;

    @Before
    public void setUp(){

       weatherresponse= Injection.provideFakeForeCastResponse();

    }
    @Test
    public void shouldGetdatafromNetwork(){

        when(remoteRepo.getForecaseWeather(LAT,LON)).thenReturn(Observable.just(weatherresponse));

        presenter.getForeCastWeather(LAT,LON);

        verify(localRepo).createRalmList(weatherresponse);

    }


}