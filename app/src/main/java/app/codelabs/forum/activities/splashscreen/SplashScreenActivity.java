package app.codelabs.forum.activities.splashscreen;

import androidx.appcompat.app.AppCompatActivity;
import app.codelabs.forum.MainActivity;
import app.codelabs.forum.R;
import app.codelabs.forum.activities.forgotpassword.ForgotPasswordActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.se.omapi.Session;

public class SplashScreenActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent =  new Intent(SplashScreenActivity.this, MainActivity.class);
                startActivity(intent);

                finish();
            }
        }, 3000);
    }


    }


