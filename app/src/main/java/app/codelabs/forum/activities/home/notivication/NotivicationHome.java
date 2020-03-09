package app.codelabs.forum.activities.home.notivication;

import androidx.appcompat.app.AppCompatActivity;
import app.codelabs.forum.R;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class NotivicationHome extends AppCompatActivity {
ImageView panah_kiri,img_notivi,img_notiva;
TextView txtnotiv,txtmenit,txtmin,txtvot,txtvotingperiod,txtdescvot,txtdescvotper;
View vline,vlines;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notivication_home);

        setView();
    }

    private void setView() {
        panah_kiri = findViewById(R.id.img_panahkiri);
        img_notivi = findViewById(R.id.img_notiv);
        img_notiva = findViewById(R.id.img_notiva);
        txtnotiv = findViewById(R.id.txtnotif);
        txtvot = findViewById(R.id.txtvoting);
        txtvotingperiod = findViewById(R.id.txtvoperiod);
        txtdescvot = findViewById(R.id.txtdescvoting);
        txtdescvotper = findViewById(R.id.txtdescvoperiod);
        txtmenit = findViewById(R.id.txtmenit);
        txtmin = findViewById(R.id.txtmin);
        vline = findViewById(R.id.txtline);
        vlines = findViewById(R.id.txtlines);
    }
}
