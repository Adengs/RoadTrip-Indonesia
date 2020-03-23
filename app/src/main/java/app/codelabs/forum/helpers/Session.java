package app.codelabs.forum.helpers;

import android.content.Context;
import android.content.SharedPreferences;

public class Session {
    private static String KEY_TOKEN = "token";
    private static String KEY_APP_TOKEN="app-token";
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

    public String getToken(){
        String token =sharedPreferences.getString(KEY_TOKEN,"");
        return token;
    }
    public Boolean islogin() {
        Boolean login = sharedPreferences.getBoolean("sudah_login", false);
        return login;
    }
    public void  setlogin(){
        editor.putBoolean("sudah_login",true);
        editor.commit();
    }



    public void setDataLogin(String token, int id, int company_id, String username, String name, String email, String city, String date_birth, String photo, String role) {
        editor.putInt("id",id);
        editor.putInt("company_id",company_id);
        editor.putString("email",email);
        editor.putString("username",username);
        editor.putString("name",name);
        editor.putString("photo",photo);
        editor.putString("date_birth",date_birth);
        editor.putString("city",city);
        editor.putString("role",role);
        editor.putString(KEY_TOKEN,token);
        editor.commit();
    }
    public void setLogout(){
        editor.remove("id");
        editor.remove("company_id");
        editor.remove("email");
        editor.remove("username");
        editor.remove("name");
        editor.remove("photo");
        editor.remove("date_birth");
        editor.remove("city");
        editor.remove("role");
        editor.remove(KEY_TOKEN);
        editor.commit();
    }
}
