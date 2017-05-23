package com.example.thedeveloper.dbflowappdemo.configs;

import android.app.Application;

import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;

/**
 * Created by The Developer on 5/22/2017.
 */

public class AppConfig extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        FlowManager.init(new FlowConfig.Builder(this).build());
    }
}
