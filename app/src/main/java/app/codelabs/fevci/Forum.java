package app.codelabs.fevci;

import android.app.Application;

import net.danlew.android.joda.JodaTimeAndroid;

import app.codelabs.fevci.helpers.SocketSingleton;

/**
 * Created by edinofri
 * tukangbasic@gmail.com
 * 13 Apr 2020, 11:16
 */
public class Forum extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        JodaTimeAndroid.init(this);
        SocketSingleton.init();
    }

}
