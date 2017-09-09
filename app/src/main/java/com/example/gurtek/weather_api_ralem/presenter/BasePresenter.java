package com.example.gurtek.weather_api_ralem.presenter;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * * Created by Gurtek Singh on 9/9/2017.
 * Sachtech Solution
 * gurtekjattx@gmail.com .
 */

public class BasePresenter {

    private boolean isTesting;

    public BasePresenter(boolean isTesting) {
        this.isTesting = isTesting;
    }

     Scheduler onIo() {
        if (isTesting) return Schedulers.trampoline();
        else  return Schedulers.io();
    }

    Scheduler onComputation() {
        if (isTesting) return Schedulers.trampoline();
        else  return Schedulers.computation();
    }

     Scheduler onMain() {
        if (isTesting) return Schedulers.trampoline();
        else  return AndroidSchedulers.mainThread();
    }


}
