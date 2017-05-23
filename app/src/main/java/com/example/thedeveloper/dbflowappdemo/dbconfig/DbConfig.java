package com.example.thedeveloper.dbflowappdemo.dbconfig;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by The Developer on 5/22/2017.
 */

@Database(name = DbConfig.NAME , version = DbConfig.VERSION)
public class DbConfig {
    public static final String NAME = "productdb";
    public static final int VERSION = 1;

}
