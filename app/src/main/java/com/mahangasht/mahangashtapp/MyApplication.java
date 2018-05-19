package com.mahangasht.mahangashtapp;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.mahangasht.mahangashtapp.Model.ServiceType;

import java.util.ArrayList;

/**
 * Created by Amin on 2/26/2017.
 */

public class MyApplication extends Application {

    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public static ArrayList<ServiceType> myServiceType = null;

}
