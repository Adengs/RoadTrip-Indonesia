package app.codelabs.forum.activities.splashscreen;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import androidx.appcompat.app.AppCompatActivity;
import app.codelabs.forum.R;
import app.codelabs.forum.activities.home.HomeActivity;
import app.codelabs.forum.activities.walktrought.WalkThroughActivity;
import app.codelabs.forum.helpers.ConnectionApi;
import app.codelabs.forum.helpers.Session;
import app.codelabs.forum.models.ResponWalkThrough;
import app.codelabs.forum.models.ResponseApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashScreenActivity extends AppCompatActivity {
Context context;
String AppToken;
Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        /*new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreenActivity.this, WalkThroughActivity.class);
                startActivity(intent);
                finish();
            }
        }, 3000);*/

        context = getApplicationContext();
        session = Session.init(context);


        loadAppToken();

    }

    private void loadwalktrough() {
        ConnectionApi.apiService().Walkthrough(AppToken).enqueue(new Callback<ResponWalkThrough>() {
            @Override
            public void onResponse(Call<ResponWalkThrough> call, Response<ResponWalkThrough> response) {
                if (response.isSuccessful() && response.body().getSuccess()){
                    Gson gson = new Gson();
                    Boolean  login = session.islogin();
                    if(login == true){//jika bisa bernilai benar maka Splachscreen pergi ke home
                        startActivity(new Intent(SplashScreenActivity.this, HomeActivity.class));

                    }else{//jika bisa bernilai tidakbenar  maka Splachscreen pergi ke mainactivity
                        Intent intent = new Intent(SplashScreenActivity.this, WalkThroughActivity.class);
                        intent.putExtra("title",gson.toJson(response.body().getData()));
                        startActivity(intent);
                    }


                }else {
                    Toast.makeText(context,response.body().getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<ResponWalkThrough> call, Throwable t) {
                Toast.makeText(context,t.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });
    }


    private void loadAppToken() {
        Map<String, String> body = new HashMap<>();
        body.put("company_name","fevci");
        body.put("comp_key","fevci123");
        ConnectionApi.apiService().apptoken(body).enqueue(new Callback<ResponseApi>() {
            @Override
            public void onResponse(Call<ResponseApi> call, Response<ResponseApi> response) {
                if (response.isSuccessful() && response.body().getSuccess()){
                    Toast.makeText(context,"Welcome",Toast.LENGTH_SHORT).show();
                    session.setApptoken(response.body().getData());
                    AppToken = session.getAppToken();
                    loadwalktrough();

                }else{
                    Toast.makeText(context,response.body().getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<ResponseApi> call, Throwable t) {
                Toast.makeText(context, t.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });
    }


}


