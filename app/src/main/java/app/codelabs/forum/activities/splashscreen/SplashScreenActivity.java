package app.codelabs.forum.activities.splashscreen;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

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
    private Context context;
    private String AppToken;
    private Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        context = getApplicationContext();
        session = Session.init(context);
        loadAppToken();

    }

    private void loadWalkTrough() {
        ConnectionApi.apiService().getWalkTrough(AppToken).enqueue(new Callback<ResponWalkThrough>() {
            @Override
            public void onResponse(Call<ResponWalkThrough> call, Response<ResponWalkThrough> response) {
                if (response.isSuccessful() && response.body().getSuccess()) {
                    Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                   //if (session.isLogin()) {
                      //Intent intent = new Intent(SplashScreenActivity.this, HomeActivity.class);
                       //startActivity(intent);
                       // finish();
                   // } else {
                        Intent intent = new Intent(SplashScreenActivity.this, WalkThroughActivity.class);
                        session.setWalkTrough(response.body().getData());
                        startActivity(intent);
                   // }
                    finish();
                } else {
                    Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponWalkThrough> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }


    private void loadAppToken() {
        Map<String, String> body = new HashMap<>();
        body.put("company_name", "fevci");
        body.put("comp_key", "fevci123");
        ConnectionApi.apiService().getAppToken(body).enqueue(new Callback<ResponseApi>() {
            @Override
            public void onResponse(Call<ResponseApi> call, Response<ResponseApi> response) {
                if (response.isSuccessful() && response.body().getSuccess()) {
                    Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    session.setAppToken(response.body().getData());
                    AppToken = session.getAppToken();
                    loadWalkTrough();

                } else {
                    Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseApi> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }


}


