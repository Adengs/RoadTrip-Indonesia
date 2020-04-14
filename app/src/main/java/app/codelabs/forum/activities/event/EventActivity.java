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
import app.codelabs.forum.activities.event.adapter.AdapterTapLayoutEvent;
import app.codelabs.forum.activities.event.chat.ChatFragment;
import app.codelabs.forum.activities.event.description.DescriptionFragment;
import app.codelabs.forum.activities.event.participant.ParticipantFragment;
import app.codelabs.forum.activities.event.schedule.ScheduleFragment;
import app.codelabs.forum.activities.event.walkietalkie.WalkieTalkieFragment;
import app.codelabs.forum.models.ResponsListEventCommunity;

public class EventActivity extends AppCompatActivity {
    private Context context;
    private TabLayout tabLayoutEvent;
    private ViewPager viewPagerEvent;
    private Boolean isJoin;
    private String strData;
    private ResponsListEventCommunity.DataEntity data;
    private Toolbar toolbar;


    AdapterTapLayoutEvent adapter;

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
            isJoin = getIntent().getBooleanExtra("is_join",false);
            strData = getIntent().getStringExtra("data");
            data = new Gson().fromJson(strData, ResponsListEventCommunity.DataEntity.class);
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
         adapter=new AdapterTapLayoutEvent(getSupportFragmentManager());

         Bundle ags = new Bundle();
         ags.putString("data",new Gson().toJson(data));
         ags.putBoolean("is_join",false);

         DescriptionFragment descriptionFragment = new DescriptionFragment();
         descriptionFragment.setArguments(ags);
         adapter.addFragment(descriptionFragment,"Description");

         ParticipantFragment participantFragment = new ParticipantFragment();
         participantFragment.setArguments(ags);
         adapter.addFragment(participantFragment,"Participant");

         adapter.addFragment(new ScheduleFragment(),"Schedule");
         adapter.addFragment(new WalkieTalkieFragment(),"Walkie Talkie");
         adapter.addFragment(new ChatFragment(),"Chat");
         viewPagerEvent.setAdapter(adapter);

         tabLayoutEvent.setupWithViewPager(viewPagerEvent);

    }

    private void setView() {
        tabLayoutEvent = findViewById(R.id.tab_layout);
        viewPagerEvent = findViewById(R.id.viewPager);
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
