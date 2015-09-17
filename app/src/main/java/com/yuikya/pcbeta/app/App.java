package com.yuikya.pcbeta.app;

import android.app.Application;
import android.content.Context;

/**
 * Created by fan on 2015/9/17.
 */
public class App extends Application {
    private static App instance;
    public static App getInstance(){
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
}
