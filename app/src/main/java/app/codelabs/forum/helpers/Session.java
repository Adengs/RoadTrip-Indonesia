package app.codelabs.forum.helpers;

import android.content.Context;
import android.content.SharedPreferences;

public class Session {
    private SharedPreferences sharedPreferences;
    private Context context;

    public Session(Context context){
        sharedPreferences = context.getSharedPreferences("Settings",Context.MODE_PRIVATE);
    }
    public static Session init(Context context){
        return new Session(context);
    }

    public String getAppToken(){
        String apptoken = sharedPreferences.getString("data","");
        return  apptoken;
    }

}
