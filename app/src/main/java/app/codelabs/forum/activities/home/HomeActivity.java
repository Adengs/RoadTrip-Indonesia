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
import app.codelabs.forum.activities.home.fragment.HomeFragment;
import app.codelabs.forum.activities.home.notivication.NotivicationHome;

public class HomeActivity extends AppCompatActivity {
Context context;
TextView txtfevci;
ImageView img_bell, imgHomes, imgClubs,imgChats,imgEvents,imgProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        setView();
        setEvent();



        setFragment(new HomeFragment());

    }


    private void setView() {
        txtfevci = findViewById(R.id.txtfevci);
        img_bell = findViewById(R.id.iconbell);
        imgHomes = findViewById(R.id.img_homes);
        imgClubs = findViewById(R.id.img_clubs);
        imgChats = findViewById(R.id.img_chats);
        imgEvents = findViewById(R.id.img_events);
        imgProfile = findViewById(R.id.img_profiles);
    }


    private void setEvent() {
        img_bell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, NotivicationHome.class);
                startActivity(intent);
            }
        });
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

            }
        });
        imgEvents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        imgProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
