package com.example.gurtek.weather_api_ralem.errors;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;

import static com.example.gurtek.weather_api_ralem.errors.ErrorMode.DATA_NOT_AVAILBALE;
import static com.example.gurtek.weather_api_ralem.errors.ErrorMode.INTERNET_NOT_AVAIlBALE;
import static java.lang.annotation.RetentionPolicy.SOURCE;

@Retention(SOURCE)
@IntDef({INTERNET_NOT_AVAIlBALE,DATA_NOT_AVAILBALE})
public @interface ErrorMode {
    public static final int INTERNET_NOT_AVAIlBALE = 0;
    public static final int DATA_NOT_AVAILBALE = 1;
}


