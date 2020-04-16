package app.codelabs.forum.activities.event;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;

import app.codelabs.forum.R;
import app.codelabs.forum.activities.event.adapter.EventPagerAdapter;
import app.codelabs.forum.activities.event.fragment.ChatFragment;
import app.codelabs.forum.activities.event.fragment.DescriptionFragment;
import app.codelabs.forum.activities.event.fragment.ParticipantFragment;
import app.codelabs.forum.activities.event.fragment.ScheduleFragment;
import app.codelabs.forum.activities.event.fragment.WalkieTalkieFragment;
import app.codelabs.forum.models.ResponseListEventCommunity;

public class EventActivity extends AppCompatActivity {
    public static final int REQ_REFRESH_EVENT = 1001;
    private Context context;
    private TabLayout tabLayoutEvent;
    private ViewPager viewPagerEvent;
    private String strData;
    public ResponseListEventCommunity.DataEntity data;
    private Toolbar toolbar;
    public int selectedIndex = -1;

    private EventPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        context = getApplicationContext();

        setView();
        getData();
        setViewPager();
        setToolbar();

    }

    private void getData() {
        if (getIntent().getStringExtra("data") != null) {
            strData = getIntent().getStringExtra("data");
            selectedIndex = getIntent().getIntExtra("index",-1);
            data = new Gson().fromJson(strData, ResponseListEventCommunity.DataEntity.class);
        }
    }

    private void setToolbar() {
        setSupportActionBar(toolbar);
        setTitle("");
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    private void setViewPager() {
        adapter = new EventPagerAdapter(getSupportFragmentManager());

        adapter.addFragment(new DescriptionFragment(), "Description");
        adapter.addFragment( new ParticipantFragment(), "Participant");
        adapter.addFragment(new ScheduleFragment(), "Schedule");
        adapter.addFragment(new WalkieTalkieFragment(), "Walkie Talkie");
        adapter.addFragment(new ChatFragment(), "Chat");
        viewPagerEvent.setAdapter(adapter);

        tabLayoutEvent.setupWithViewPager(viewPagerEvent);

    }

    private void setView() {
        tabLayoutEvent = findViewById(R.id.tab_layoutevent);
        viewPagerEvent = findViewById(R.id.viewpagerevent);
        toolbar = findViewById(R.id.toolbar);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
