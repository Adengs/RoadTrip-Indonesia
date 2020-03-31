package app.codelabs.forum.helpers;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import app.codelabs.forum.activities.login.LoginActivity;
import app.codelabs.forum.models.ResponsLogin;

public class Session {
    private static String KEY_TOKEN = "token";
    private static String KEY_APP_TOKEN = "app-token";
    private static String KEY_IS_LOGIN = "is-login";
    private static String KEY_USER = "user";

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Context context;

    public Session(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences("session-forum", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public static Session init(Context context) {
        return new Session(context);
    }

    public String getAppToken() {
        return sharedPreferences.getString(KEY_APP_TOKEN, "");
    }

    public void setAppToken(String app_token) {
        editor.putString(KEY_APP_TOKEN, app_token);
        editor.commit();
    }

    public String getToken() {
        return sharedPreferences.getString(KEY_TOKEN, "");
    }

    public Boolean isLogin() {
        return sharedPreferences.getBoolean(KEY_IS_LOGIN, false);
    }

    public void setLogin(ResponsLogin.Data user, String token) {
        editor.putBoolean(KEY_IS_LOGIN, true);
        editor.putString(KEY_USER, new Gson().toJson(user));
        editor.putString(KEY_TOKEN, token);
        editor.commit();
    }

    public ResponsLogin.Data getUser() {
        return new Gson().fromJson(sharedPreferences.getString(KEY_USER, "{}"), ResponsLogin.Data.class);
    }

    public void setLogout() {
        editor.clear();
        editor.commit();
        context.startActivity(new Intent(context, LoginActivity.class)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
    }
}
