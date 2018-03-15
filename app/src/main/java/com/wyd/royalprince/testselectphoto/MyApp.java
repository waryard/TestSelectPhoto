package com.wyd.royalprince.testselectphoto;

import android.app.Application;

/**
 * Created by wyd on 2018/3/15.
 */

public class MyApp extends Application {
    private static MyApp instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static MyApp getInstance() {
        return instance;
    }
}
