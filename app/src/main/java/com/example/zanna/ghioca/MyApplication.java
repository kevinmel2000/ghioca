package com.example.zanna.ghioca;

import android.app.Application;
import android.os.Environment;

/**
 * @author Marco Zanella
 * @version 0.01
 * @since 0.01
 */

public class MyApplication extends Application {

    //private static Context instance;
    public static final String MY_API_KEY = "AkHqkinKScahBDKyXuqzQz";
    public static final String appFolderName = "GHiO-CA";
    public static final String appFolderPath = Environment.getExternalStoragePublicDirectory(
            Environment.DIRECTORY_PICTURES).getAbsolutePath() + "/" + appFolderName;
    /*@Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static Context getAppContext() {
        return instance;
    }*/
}