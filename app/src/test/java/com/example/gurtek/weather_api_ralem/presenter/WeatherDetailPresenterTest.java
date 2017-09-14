package com.example.gurtek.weather_api_ralem.presenter;

import com.example.gurtek.weather_api_ralem.injection.Injection;
import com.example.gurtek.weather_api_ralem.interfaces.DataBaseRepo;
import com.example.gurtek.weather_api_ralem.interfaces.GetweatherView;
import com.example.gurtek.weather_api_ralem.interfaces.weatherDListener.WeatherDetail;
import com.example.gurtek.weather_api_ralem.models.WeatherLocation;
import com.example.gurtek.weather_api_ralem.models.WeatherRealm;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import javax.inject.Inject;

import io.reactivex.Observable;

import static org.junit.Assert.*;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * * Created by Gurtek Singh on 9/13/2017.
 * Sachtech Solution
 * gurtekjattx@gmail.com .
 */
public class WeatherDetailPresenterTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    WeatherDetail.NetworkRepository remoteRepo;

    @Mock DataBaseRepo localRepo;

    @Mock
    WeatherDetail.WeatherDetailView view;

    private double LAT=12;
    private double LON=12;

    @InjectMocks WeatherDetailPresenter presenter;
    private WeatherLocation response;

    private WeatherRealm weatherLocalModel;

    @Before
    public void setUp(){

        response = Injection.provideLocationResponse();
        weatherLocalModel=Injection.provideFakeWeatherRalm();
    }

    @Test
    public void shouldGiveDataToWriteInLocal(){

       when(remoteRepo.getWeatherByLocation(LAT,LON)).thenReturn(Observable.just(response));

       presenter.getCurrentWeather(LAT,LON);

       verify(localRepo).writeTodataBase(response);

    }

    @Test
    public void shouldReadDataFromLocal(){

        when(remoteRepo.getWeatherByLocation(LAT,LON)).thenReturn(Observable.just(response));

        when(localRepo.writeTodataBase(response)).thenReturn(response.getId());

        presenter.getCurrentWeather(LAT,LON);

        verify(localRepo,atLeast(1)).readfromDataBase(response.getId());


    }

    @Test
    public void shouldHandleError(){

        when(remoteRepo.getWeatherByLocation(LAT,LON)).thenReturn(Observable.error(new Throwable("Boom!")));

        presenter.getCurrentWeather(LAT,LON);

        verify(view).onError("Boom!");

    }

    @Test public void shouldDisplayDataIfAvailable(){

        when(localRepo.readfromDataBase()).thenReturn(weatherLocalModel);

        when(remoteRepo.getWeatherByLocation(LAT,LON)).thenReturn(Observable.error(new Throwable("Boom!")));


        presenter.getCurrentWeather(LAT,LON);

        verify(view).onCurrentWeatherRecived(weatherLocalModel);

    }


}