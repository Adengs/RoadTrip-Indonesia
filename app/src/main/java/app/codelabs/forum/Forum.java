package app.codelabs.forum;

import android.app.Application;

import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import net.danlew.android.joda.JodaTimeAndroid;

import java.net.URISyntaxException;

import app.codelabs.forum.helpers.SocketSingleton;

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
