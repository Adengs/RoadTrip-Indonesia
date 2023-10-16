package app.codelabs.roadtrip.activities.setting;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import app.codelabs.roadtrip.R;
import app.codelabs.roadtrip.helpers.Session;

public class SettingActivity extends AppCompatActivity {
    Button btnLogout;
    TextView btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        setView();
        setEvent();

    }

    private void setEvent() {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Session.init(getApplicationContext()).setLogout();
            }
        });
    }

    private void setView() {
        btnBack = findViewById(R.id.btn_back);
        btnLogout = findViewById(R.id.btn_logout);
    }
}
