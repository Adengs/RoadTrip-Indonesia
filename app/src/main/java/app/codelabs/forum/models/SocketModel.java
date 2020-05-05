package app.codelabs.forum.models;

import com.google.gson.Gson;

/**
 * Created by edinofri
 * tukangbasic@gmail.com
 * 24 Apr 2020, 13:40
 */
public class SocketModel {
    public String toJson() {
        return new Gson().toJson(this);
    }
}
