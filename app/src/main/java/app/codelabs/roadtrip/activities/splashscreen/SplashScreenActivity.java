package app.codelabs.roadtrip.activities.splashscreen;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Map;

import app.codelabs.roadtrip.activities.home.HomeActivity;
import app.codelabs.roadtrip.helpers.ConnectionApi;
import app.codelabs.roadtrip.helpers.Constant;
import app.codelabs.roadtrip.helpers.Session;
import app.codelabs.roadtrip.models.ResponseApi;
import app.codelabs.roadtrip.R;
import app.codelabs.roadtrip.activities.walktrough.WalkThroughActivity;
import app.codelabs.roadtrip.models.ResponseWalkThrough;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashScreenActivity extends AppCompatActivity {
    private Context context;
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
        ConnectionApi.apiService(context).getWalkTrough().enqueue(new Callback<ResponseWalkThrough>() {
            @Override
            public void onResponse(Call<ResponseWalkThrough> call, Response<ResponseWalkThrough> response) {
                if (response.body() != null) {
                    if (response.isSuccessful() && response.body().getSuccess()) {
                        if (session.isLogin()) {
                            Intent intent = new Intent(SplashScreenActivity.this, HomeActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Intent intent = new Intent(SplashScreenActivity.this, WalkThroughActivity.class);
                            session.setWalkTrough(response.body().getData());
                            startActivity(intent);
                        }
                        finish();
                    } else {
                        Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(context, response.message(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ResponseWalkThrough> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }


    private void loadAppToken() {
        Map<String, String> body = new HashMap<>();
        body.put("company_name", Constant.COMPANY_NAME);
        body.put("comp_key", Constant.COMPANY_KEY);
        ConnectionApi.apiService(context).getAppToken(body).enqueue(new Callback<ResponseApi>() {
            @Override
            public void onResponse(Call<ResponseApi> call, Response<ResponseApi> response) {
                if (response.isSuccessful() && response.body().getSuccess()) {
                    session.setAppToken(response.body().getData().getApp_token());
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


