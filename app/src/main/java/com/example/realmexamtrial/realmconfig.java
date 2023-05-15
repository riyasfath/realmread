package com.example.realmexamtrial;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class realmconfig extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        io.realm.RealmConfiguration configuration=new RealmConfiguration.Builder().allowQueriesOnUiThread(true).allowWritesOnUiThread(true).deleteRealmIfMigrationNeeded().build();
        Realm.setDefaultConfiguration(configuration);
    }
}
