package app.codelabs.fevci.activities.notification;

import androidx.appcompat.app.AppCompatActivity;

import app.codelabs.forum.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class NotificationDetailActivity extends AppCompatActivity {
    TextView txtvotingperiod, carname, timevot, datevot, descvot1, descvot2;
    ImageView leftpanah, circlevot;
    View vlines, linevot;
    Button btn_vot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_detail);

        setView();
        setEvent();
    }

    private void setView() {
        txtvotingperiod = findViewById(R.id.txtvotingperiod);
        carname = findViewById(R.id.carname);
        timevot = findViewById(R.id.timevot);
        datevot = findViewById(R.id.datevot);
        descvot1 = findViewById(R.id.descvot1);
        descvot2 = findViewById(R.id.descvot2);
        leftpanah = findViewById(R.id.img_panahleft);
        circlevot = findViewById(R.id.circlevot);
        vlines = findViewById(R.id.vlines);
        linevot = findViewById(R.id.linevot);
        btn_vot = findViewById(R.id.btn_vot);
    }

    private void setEvent() {
        leftpanah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NotificationDetailActivity.this, NotificationActivity.class);
                startActivity(intent);
            }
        });
        btn_vot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
