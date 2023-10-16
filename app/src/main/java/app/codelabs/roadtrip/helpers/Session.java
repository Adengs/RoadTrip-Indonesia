package app.codelabs.roadtrip.helpers;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import app.codelabs.roadtrip.models.MemberProfile;
import app.codelabs.roadtrip.models.ResponseLogin;
import app.codelabs.roadtrip.activities.login.LoginActivity;
import app.codelabs.roadtrip.models.ResponseMyProfile;
import app.codelabs.roadtrip.models.ResponseWalkThrough;

public class Session {
    private static String KEY_TOKEN = "token";
    private static String KEY_APP_TOKEN = "app-token";
    private static String KEY_WALK_TROUGH = "walk-trough";
    private static String KEY_IS_LOGIN = "is-login";
    private static String KEY_USER = "user";
    private static String KEY_MEMBER = "member";
    private static String KEY_PROFILE = "profile";

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

    public void setLogin(ResponseLogin.Data user, String token) {
        editor.putBoolean(KEY_IS_LOGIN, true);
        editor.putString(KEY_TOKEN, token);
        editor.commit();
        setUser(user);
    }

    public void setProfile(ResponseMyProfile.Data profile) {
//        editor.putBoolean(KEY_IS_LOGIN, true);
        editor.commit();
        setProfil(profile);
    }

    private void setProfil(ResponseMyProfile.Data profil) {
        editor.putString(KEY_PROFILE, new Gson().toJson(profil));
        editor.commit();
    }

    public ResponseMyProfile.Data getProfile() {
        return new Gson().fromJson(sharedPreferences.getString(KEY_PROFILE, "{}"), ResponseMyProfile.Data.class);
    }

    public ResponseLogin.Data getUser() {
        return new Gson().fromJson(sharedPreferences.getString(KEY_USER, "{}"), ResponseLogin.Data.class);
    }

    public MemberProfile.Data getMember() {
        return new Gson().fromJson(sharedPreferences.getString(KEY_MEMBER, "{}"), MemberProfile.Data.class);
    }

    public void setLogout() {

        editor.remove(KEY_IS_LOGIN);
        editor.remove(KEY_USER);
        editor.remove(KEY_TOKEN);
        editor.commit();
        context.startActivity(new Intent(context, LoginActivity.class)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
    }

    public void setWalkTrough(List<ResponseWalkThrough.DataEntity> data) {
        editor.putString(KEY_WALK_TROUGH, new Gson().toJson(data));
        editor.commit();
    }

    public List<ResponseWalkThrough.DataEntity> getWalkTrough() {
        List<ResponseWalkThrough.DataEntity> items = new Gson().fromJson(sharedPreferences.getString(KEY_WALK_TROUGH, "[]"),
                new TypeToken<List<ResponseWalkThrough.DataEntity>>() {
                }.getType());
        return items;
    }

    public void setUser(ResponseLogin.Data user) {
        editor.putString(KEY_USER, new Gson().toJson(user));
        editor.commit();
    }

    public void setMember(MemberProfile.Data member) {
        editor.putString(KEY_MEMBER, new Gson().toJson(member));
        editor.commit();
    }
}
