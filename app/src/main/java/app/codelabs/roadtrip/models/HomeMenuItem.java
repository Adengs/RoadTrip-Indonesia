package app.codelabs.roadtrip.models;

import android.app.Activity;

/**
 * Created by edinofri
 * tukangbasic@gmail.com
 * 23 Mar 2020, 14:45
 */
public class HomeMenuItem {
    public HomeMenuItem(int icon, String name, Class<? extends Activity> activity) {
        this.icon = icon;
        this.name = name;
        this.activity = activity;
    }

    private int icon;
    private String name;
    private Class<? extends Activity> activity;

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Class<? extends Activity> getActivity() {
        return activity;
    }

    public void setActivity(Class<? extends Activity> activity) {
        this.activity = activity;
    }
}
