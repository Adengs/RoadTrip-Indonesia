package app.codelabs.roadtrip.activities.notification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import app.codelabs.roadtrip.activities.home.HomeActivity;
import app.codelabs.roadtrip.activities.notification.adapter.NotificationAdapter;
import app.codelabs.roadtrip.R;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class NotificationActivity extends AppCompatActivity {
    ImageView panah_kiri;
    TextView txtnotiv;
    RecyclerView recyclerView;
    NotificationAdapter adapter;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        adapter = new NotificationAdapter();

        setView();
        setEvent();
        setRecyclerView();
    }

    private void setRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapter);
    }

    private void setEvent() {
        panah_kiri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NotificationActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setView() {
        recyclerView = findViewById(R.id.recyclerviewnotif);
        panah_kiri = findViewById(R.id.img_panahkiri);
        txtnotiv = findViewById(R.id.txtnotif);
    }
}
