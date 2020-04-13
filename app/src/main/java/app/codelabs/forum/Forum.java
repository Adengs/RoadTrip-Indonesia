package app.codelabs.forum;

import android.app.Application;

import net.danlew.android.joda.JodaTimeAndroid;

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
    }
}
