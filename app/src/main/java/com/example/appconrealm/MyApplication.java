package com.example.appconrealm;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by dam2a on 13/04/18.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);

        /*
        *
        * RealmConfiguration config = new RealmConfiguration.Builder().build();
        * En caso de poner esa linea la ruta seria esta:
        * /data/data/com.example.appconrealm/files/default.realm
        *
        * */

        RealmConfiguration config = new RealmConfiguration.Builder().name("default.realm")
                .schemaVersion(1)
                .migration(new Migration())
                .build();

        Realm.setDefaultConfiguration(config);

    }
}