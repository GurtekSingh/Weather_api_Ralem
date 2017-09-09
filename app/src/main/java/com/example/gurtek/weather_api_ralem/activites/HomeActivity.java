package com.example.gurtek.weather_api_ralem.activites;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.gurtek.weather_api_ralem.BaseActivity;
import com.example.gurtek.weather_api_ralem.R;
import com.example.gurtek.weather_api_ralem.di.components.DaggerHomeActivityComponent;
import com.example.gurtek.weather_api_ralem.di.components.HomeActivityComponent;
import com.example.gurtek.weather_api_ralem.di.modules.HomeActivityModule;
import com.example.gurtek.weather_api_ralem.interfaces.GetweatherView;
import com.example.gurtek.weather_api_ralem.models.WeatherRealm;
import com.example.gurtek.weather_api_ralem.presenter.WeatherPresenter;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.subscribers.ResourceSubscriber;

public class HomeActivity extends BaseActivity implements GetweatherView {


    @BindView(R.id.humidity)
    TextView humidity;
    @BindView(R.id.temp_min)
    TextView tempMin;
    @BindView(R.id.temp_max)
    TextView tempMax;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.cityName)
    TextView cityName;
    private HomeActivityComponent activityComponent;


    @Inject
    WeatherPresenter presenter;

    String LAT="30.719027";
    String LON="76.706165";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        initDagger();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(view -> {

              presenter.getData(464737);

            Observable.fromArray(464737, 361478, 361827, 360615, 361329)
                    .delay(integer -> Observable.just(integer).delay(20,TimeUnit.SECONDS).skip(1))
                    .subscribe(integer ->  Log.e("Delayed", "" + Calendar.getInstance().getTime()));



        });

        presenter.getWeatherInfo(LAT,LON);

    }

    private void initDagger() {

        activityComponent = DaggerHomeActivityComponent.builder()
                .appComponent(getComponent())
                .homeActivityModule(new HomeActivityModule(this))
                .build();
        activityComponent.inject(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onWeatherDataRecive(WeatherRealm weatherRealm) {

        Log.e("TAG", "" + weatherRealm);

        tempMin.setText(String.valueOf(weatherRealm.getTempMin()));
        tempMax.setText(String.valueOf(weatherRealm.getTempMax()));
        humidity.setText(String.valueOf(weatherRealm.getHumidity()));
        cityName.setText(weatherRealm.getCityName());

    }

    @Override
    public void onError(String error) {

        showAlertDialog(error);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.unSubscribe();
    }
}
