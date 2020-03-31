package app.codelabs.forum.activities.profile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import app.codelabs.forum.R;
import app.codelabs.forum.activities.home.HomeActivity;
import app.codelabs.forum.activities.login.LoginActivity;
import app.codelabs.forum.helpers.Session;

public class SettingProfile extends AppCompatActivity {
    Button btnLogoutSetPro;
    TextView backsettingPro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_profile);


        setView();
        setEvent();

    }

    private void setEvent() {
        backsettingPro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SettingProfile.this, HomeActivity.class));
            }
        });
        btnLogoutSetPro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Session.init(getApplicationContext()).setLogout();
                startActivity(new Intent(SettingProfile.this, LoginActivity.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
            }
        });
    }

    private void setView() {
        backsettingPro = findViewById(R.id.back_settingProfile);
        btnLogoutSetPro = findViewById(R.id.btnlogoutSetting);
    }
}
