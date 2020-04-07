package app.codelabs.forum.activities.home.latest_popular_foryou;

import androidx.appcompat.app.AppCompatActivity;

import app.codelabs.forum.R;
import app.codelabs.forum.activities.home.HomeActivity;
import app.codelabs.forum.activities.home.fragment.HomeFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class CardViewActivity extends AppCompatActivity {
    TextView txtcarhome, timehome, datehome, txtmobilhome, deschome;
    ImageView img_panahkirihome, img_savedhome, img_sharehome, img_logohome, img_carhome, circlehome;
    View vwline, linehome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_view);

        setView();
        setEvent();
    }

    private void setView() {
        txtcarhome = findViewById(R.id.txtcarhome);
        timehome = findViewById(R.id.timehome);
        circlehome = findViewById(R.id.circlehome);
        datehome = findViewById(R.id.datehome);
        txtmobilhome = findViewById(R.id.txtmobilhome);
        deschome = findViewById(R.id.deschome);
        img_panahkirihome = findViewById(R.id.img_panahkirihome);
        img_savedhome = findViewById(R.id.img_savedhome);
        img_sharehome = findViewById(R.id.img_sharehome);
        img_logohome = findViewById(R.id.img_logohome);
        img_carhome = findViewById(R.id.img_carhome);
        vwline = findViewById(R.id.vwline);
        linehome = findViewById(R.id.linehome);

    }

    private void setEvent() {
        img_panahkirihome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CardViewActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

    }
}
