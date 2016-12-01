package com.necisstudio.rxjava.manage;

import android.app.Application;

/**
 * Created by vim on 01/12/16.
 */

public class AppConfig extends Application {
    private static AppConfig instance;

    private RxBus bus;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        bus = new RxBus();
    }

    public static AppConfig get() {
        return instance;
    }

    public RxBus bus() {
        return bus;
    }
}
