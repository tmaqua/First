package com.murakami.fankami.first

import android.app.Application
import android.util.Log
import io.realm.Realm
import io.realm.RealmConfiguration


/**
 * Created by fankami on 西暦16/11/18.
 */

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // Initialize Realm. Should only be done once when the application starts.
        Log.d("REALM-DEBUG-LOG", "REALM INIT")
        Realm.init(this)
        val config: RealmConfiguration = RealmConfiguration.Builder()
                .schemaVersion(1)
                .name("FirstApp")
                .build()
        Realm.setDefaultConfiguration(config)
    }
}
