package app.codelabs.forum.activities.profile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import app.codelabs.forum.R;
import app.codelabs.forum.activities.login.LoginActivity;

public class SettingProfile extends AppCompatActivity {
    Button btnLogoutSetPro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_profile);


        setView();
        setEvent();

    }

    private void setEvent() {
        btnLogoutSetPro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SettingProfile.this , LoginActivity.class));
            }
        });
    }

    private void setView() {

        btnLogoutSetPro=findViewById(R.id.btnlogoutSettingP); }
}
