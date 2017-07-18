package com.example.gurtek.weather_api_ralem;

import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import com.example.gurtek.weather_api_ralem.di.components.AppComponent;
import com.example.gurtek.weather_api_ralem.interfaces.BaseListener;

/**
 * Created by Gurtek Singh on 6/13/2017.
 * Sachtech Solution
 * gurtekjattx@gmail.com
 */
public class BaseActivity extends AppCompatActivity implements BaseListener{

    private ConnectivityManager manager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        manager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    public AppComponent getComponent(){
       return  ((WeatherApp)getApplication()).getAppComponent();
    }

    @Override
    public boolean isNetworkAvailble() {
        try {
            NetworkInfo netInfo = manager.getActiveNetworkInfo();
            return netInfo != null && netInfo.isConnectedOrConnecting();
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public void showAlertDialog(String message) {

        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setMessage(message);
        builder.setNegativeButton("OK", (dialogInterface, i) -> dialogInterface.dismiss());

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
