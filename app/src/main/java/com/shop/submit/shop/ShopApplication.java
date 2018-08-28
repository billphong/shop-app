package com.shop.submit.shop;

import android.app.Application;

import com.beardedhen.androidbootstrap.TypefaceProvider;

//use https://github.com/Bearded-Hen/Android-Bootstrap
public class ShopApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        TypefaceProvider.registerDefaultIconSets();
    }
}
