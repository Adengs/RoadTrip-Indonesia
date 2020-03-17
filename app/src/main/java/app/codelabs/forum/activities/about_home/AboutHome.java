package app.codelabs.forum.activities.about_home;

import androidx.appcompat.app.AppCompatActivity;
import app.codelabs.forum.R;
import app.codelabs.forum.activities.home.HomeActivity;
import de.hdodenhof.circleimageview.CircleImageView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class AboutHome extends AppCompatActivity {
CircleImageView logoAbout;
TextView titleBackAbout,jdlAbout,txthistory,descAbout,jdlsekreAbout,descsekreAbout,txtmapsAbout,txtjdlorga;
ImageView imgAbout,img_linesabout,img_lineabout,linesabout3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_home);

        setView();
        setEvent();
    }

    private void setEvent() {
        titleBackAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AboutHome.this, HomeActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setView() {
        logoAbout = findViewById(R.id.logoAbout);
        titleBackAbout = findViewById(R.id.titleBackAbout);
        jdlAbout = findViewById(R.id.jdlAbout);
        txthistory = findViewById(R.id.txthistoryabout);
        descAbout = findViewById(R.id.descAbout);
        jdlsekreAbout = findViewById(R.id.jdlsekreAbout);
        descsekreAbout = findViewById(R.id.descsekreAbout);
        txtmapsAbout = findViewById(R.id.txtmapsAbout);
        txtjdlorga = findViewById(R.id.txtjdlorga);
        imgAbout = findViewById(R.id.imgAbout);
        img_linesabout = findViewById(R.id.img_linesabout);
        img_lineabout = findViewById(R.id.img_lineabout);
        linesabout3 = findViewById(R.id.linesabout3);
    }
}
