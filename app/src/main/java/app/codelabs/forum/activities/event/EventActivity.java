package app.codelabs.forum.activities.event;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import app.codelabs.forum.R;
import app.codelabs.forum.activities.event.adapter.AdapterEventActivity2;
import app.codelabs.forum.activities.event.chat.ChatFragment;
import app.codelabs.forum.activities.event.description.DescriptionFragment;
import app.codelabs.forum.activities.event.participant.ParticipantFragment;
import app.codelabs.forum.activities.event.schedule.ScheduleFragment;
import app.codelabs.forum.activities.event.walkietalkie.WalkieTalkieFragment;

public class EventActivity extends AppCompatActivity {
    Context context;
    TabLayout tabLayoutEvent;
    ViewPager viewPagerEvent;

    AdapterEventActivity2 adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);


        context = getApplicationContext();

        setView();

        setViewPager();

    }


    private void setViewPager() {
        adapter=new AdapterEventActivity2(getSupportFragmentManager());

        adapter.addFragment(new DescriptionFragment(),"Description");
        adapter.addFragment(new ParticipantFragment(),"Participant");
        adapter.addFragment(new ScheduleFragment(),"Schedule");
        adapter.addFragment(new WalkieTalkieFragment(),"Walkie Talkie");
        adapter.addFragment(new ChatFragment(),"Chat");
        viewPagerEvent.setAdapter(adapter);

        tabLayoutEvent.setupWithViewPager(viewPagerEvent);

    }

    private void setView() {
        tabLayoutEvent = findViewById(R.id.tab_layoutevent);
        viewPagerEvent = findViewById(R.id.viewpagerevent);

    }

}
