package app.codelabs.forum.activities.menu_event;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import app.codelabs.forum.R;
import app.codelabs.forum.activities.club.event_club.EventClubAdapter;
import app.codelabs.forum.activities.event.EventActivity;
import app.codelabs.forum.activities.home.HomeActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MenuEventActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private EventClubAdapter adapter;
    JoinPickFragment joinPickFragment = new JoinPickFragment();
    Context context;
    private ImageView img_panah_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_event);

        setView();
        setEvent();
        setRecycleView();
    }

    private void setEvent() {
        adapter.setListener(new EventClubAdapter.OnItemSelection() {
            @Override
            public void onBtnJoin() {
                joinPickFragment.show(getSupportFragmentManager(),"pick");
            }
        });
        img_panah_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuEventActivity.this, HomeActivity.class));
            }
        });
    }

    private void setRecycleView() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapter);
    }

    private void setView() {
        adapter = new EventClubAdapter();
        recyclerView = findViewById(R.id.rv_menu_event);
        img_panah_back = findViewById(R.id.btn_panah_event);
    }
}
