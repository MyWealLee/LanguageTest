package com.speedlink.languagetest;

import android.app.Application;
import android.content.Context;

/**
 * Created by lee on 16/8/23.
 */
public class MyApplication extends Application {
    private static Context context;
    private static MyApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        context = getApplicationContext();
    }

    public static MyApplication getInstance(){
        return instance;
    }
}
