package app.codelabs.forum;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import app.codelabs.forum.activities.menu_event.MenuEventActivity;

public class MainActivity extends AppCompatActivity {
    Button btn_coba;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_coba = findViewById(R.id.btncoba);
        btn_coba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MenuEventActivity.class));
            }
        });

    }




}
