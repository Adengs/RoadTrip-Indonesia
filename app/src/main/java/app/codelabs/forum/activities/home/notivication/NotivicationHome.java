package app.codelabs.forum.activities.home.notivication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import app.codelabs.forum.R;
import app.codelabs.forum.activities.home.HomeActivity;
import app.codelabs.forum.activities.home.notivication.adapter.NotificationAdapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class NotivicationHome extends AppCompatActivity {
ImageView panah_kiri;
TextView txtnotiv;
RecyclerView recyclerView;
NotificationAdapter adapter;
Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notivication_home);

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
                Intent intent = new Intent(NotivicationHome.this, HomeActivity.class);
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
