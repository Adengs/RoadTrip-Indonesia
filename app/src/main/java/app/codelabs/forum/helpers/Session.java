package app.codelabs.forum.helpers;

import android.content.Context;
import android.content.SharedPreferences;

public class Session {
    private static String KEY_APP_TOKEN="app_token";
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Context context;

    public Session(Context context){
        sharedPreferences = context.getSharedPreferences("Settings",Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }
    public static Session init(Context context){
        return new Session(context);
    }

    public String getAppToken(){
        String apptoken = sharedPreferences.getString(KEY_APP_TOKEN,"");
        return  apptoken;
    }
    public void setApptoken(String app_token){
        editor.putString(KEY_APP_TOKEN,app_token);
        editor.commit();
    }
}
