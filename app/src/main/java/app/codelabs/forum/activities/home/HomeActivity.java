package app.codelabs.forum.activities.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import app.codelabs.forum.R;
import app.codelabs.forum.activities.club.ClubFragment;
import app.codelabs.forum.activities.conversation.fragment.CoversationFragment;
import app.codelabs.forum.activities.event.fragment.EventFragment;
import app.codelabs.forum.activities.home.fragment.HomeFragment;
import app.codelabs.forum.activities.home.fragment.adapter.HomeCardSliderAdapter;
import app.codelabs.forum.activities.home.notivication.NotivicationHome;
import app.codelabs.forum.activities.profile.fragment.ProfileFragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {
Context context;
ImageView imgHomes, imgClubs,imgChats,imgEvents,imgProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        setView();
        setEvent();



        setFragment(new HomeFragment());

    }


    private void setView() {
        imgHomes = findViewById(R.id.img_homes);
        imgClubs = findViewById(R.id.img_clubs);
        imgChats = findViewById(R.id.img_chats);
        imgEvents = findViewById(R.id.img_events);
        imgProfile = findViewById(R.id.img_profiles);
    }


    private void setEvent() {
        imgHomes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(new HomeFragment());
            }
        });
        imgClubs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(new ClubFragment());

            }
        });
        imgChats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(new CoversationFragment());
            }
        });
        imgEvents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(new EventFragment());
            }
        });
        imgProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(new ProfileFragment());
            }
        });

    }

    private void setFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container,fragment);
        fragmentTransaction.commit();
    }
}
