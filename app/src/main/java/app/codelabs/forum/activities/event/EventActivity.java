package app.codelabs.forum.activities.event;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

import org.w3c.dom.Text;

import app.codelabs.forum.R;
import app.codelabs.forum.activities.event.adapter.AdapterEventActivity2;
import app.codelabs.forum.activities.event.chat.ChatFragment;
import app.codelabs.forum.activities.event.description.DescriptionFragment;
import app.codelabs.forum.activities.event.fragment.EventFragment;
import app.codelabs.forum.activities.event.participant.ParticipantFragment;
import app.codelabs.forum.activities.event.schedule.ScheduleFragment;
import app.codelabs.forum.activities.event.walkietalkie.WalkieTalkieFragment;
import app.codelabs.forum.activities.home.HomeActivity;
import app.codelabs.forum.activities.profile.SettingProfile;

public class EventActivity extends AppCompatActivity {
    Context context;
    TabLayout tabLayoutEvent;
    ViewPager viewPagerEvent;
    TextView backevent;

    AdapterEventActivity2 adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);


        context = getApplicationContext();

        setView();
        setEvent();
        setViewPager();


    }

    private void setEvent() {
        backevent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EventActivity.this, HomeActivity.class));
            }
        });
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
        backevent = findViewById(R.id.toolbar_titleBackEvent);

    }

}
