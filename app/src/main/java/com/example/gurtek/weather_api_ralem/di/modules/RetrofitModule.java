package com.example.gurtek.weather_api_ralem.di.modules;

import com.example.gurtek.weather_api_ralem.di.qualifiers.ApplicationContext;
import com.example.gurtek.weather_api_ralem.di.scops.ApplicationScop;
import com.example.gurtek.weather_api_ralem.retrofitcalls.WeatherApi;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Gurtek Singh on 6/13/2017.
 * Sachtech Solution
 * gurtekjattx@gmail.com
 */
@Module
public class RetrofitModule {

    @Provides
    @ApplicationScop
    Retrofit.Builder provideRetrofit(RxJava2CallAdapterFactory factory){
        return new Retrofit.Builder();
    }

    @Provides
    @ApplicationScop
    RxJava2CallAdapterFactory provideFactory(){
        return RxJava2CallAdapterFactory.create();
    }

    @Provides
    @ApplicationScop
    GsonConverterFactory provideGsonFactory(){
       return GsonConverterFactory.create();
    }

    @Provides
    @ApplicationScop
    WeatherApi provideWeatherApi(Retrofit.Builder builder,RxJava2CallAdapterFactory factory
            ,GsonConverterFactory gsonConverterFactory,OkHttpClient client){
        return builder
                .baseUrl("http://api.openweathermap.org/data/2.5/")
                .addCallAdapterFactory(factory)
                .addConverterFactory(gsonConverterFactory)
                .client(client)
                .build().create(WeatherApi.class);
    }

    @Provides
    @ApplicationScop
    OkHttpClient provideClient(HttpLoggingInterceptor interceptor){
        return new OkHttpClient.Builder().addInterceptor(interceptor).build();
    }

    @Provides
    HttpLoggingInterceptor provideLogger(){
        return new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    }

}
