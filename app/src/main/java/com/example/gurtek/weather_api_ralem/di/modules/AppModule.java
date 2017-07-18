package com.example.gurtek.weather_api_ralem.di.modules;

import android.app.Application;
import android.content.Context;


import com.example.gurtek.weather_api_ralem.di.qualifiers.ApplicationContext;
import com.example.gurtek.weather_api_ralem.di.scops.ApplicationScop;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Gurtek Singh on 6/13/2017.
 * Sachtech Solution
 * gurtekjattx@gmail.com
 */
@Module
public class AppModule {

    private final Application application;

    public AppModule(Application application){
        this.application=application;
    }

    @Provides
    @ApplicationScop
    @ApplicationContext
    Context provideApplicaitonContext(){
        return application;
    }


}
