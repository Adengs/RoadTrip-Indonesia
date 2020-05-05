package app.codelabs.forum.activities.home;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import org.json.JSONException;
import org.json.JSONObject;

import app.codelabs.forum.R;
import app.codelabs.forum.activities.home.fragment.community.CommunityFragment;
import app.codelabs.forum.activities.home.fragment.chat.ConversationFragment;
import app.codelabs.forum.activities.home.fragment.myevent.EventFragment;
import app.codelabs.forum.activities.home.fragment.home.HomeFragment;
import app.codelabs.forum.activities.home.fragment.profile.ProfileFragment;
import app.codelabs.forum.helpers.Session;
import app.codelabs.forum.helpers.SocketSingleton;
import app.codelabs.forum.models.SocketChatConnect;

public class HomeActivity extends AppCompatActivity {
    private ImageView imgHomes, imgClubs, imgChats, imgEvents, imgProfile;
    private HomeFragment homeFragment = new HomeFragment();
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        context = getApplicationContext();


        setView();
        setEvent();
        imgHomes.setImageDrawable(getResources().getDrawable(R.drawable.ic_home_active_klik));
        setFragment(homeFragment);

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
                setFragment(homeFragment);
                imgHomes.setImageDrawable(getResources().getDrawable(R.drawable.ic_home_active_klik));
                imgEvents.setImageDrawable(getResources().getDrawable(R.drawable.ic_event));
                imgClubs.setImageDrawable(getResources().getDrawable(R.drawable.ic_community));
                imgChats.setImageDrawable(getResources().getDrawable(R.drawable.ic_chat));
                imgProfile.setImageDrawable(getResources().getDrawable(R.drawable.ic_profile));
            }
        });
        imgClubs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(new CommunityFragment());
                imgHomes.setImageDrawable(getResources().getDrawable(R.drawable.ic_home_active));
                imgEvents.setImageDrawable(getResources().getDrawable(R.drawable.ic_event));
                imgClubs.setImageDrawable(getResources().getDrawable(R.drawable.ic_community_active));
                imgChats.setImageDrawable(getResources().getDrawable(R.drawable.ic_chat));
                imgProfile.setImageDrawable(getResources().getDrawable(R.drawable.ic_profile));
            }
        });
        imgChats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(new ConversationFragment());
                imgHomes.setImageDrawable(getResources().getDrawable(R.drawable.ic_home_active));
                imgEvents.setImageDrawable(getResources().getDrawable(R.drawable.ic_event));
                imgClubs.setImageDrawable(getResources().getDrawable(R.drawable.ic_community));
                imgChats.setImageDrawable(getResources().getDrawable(R.drawable.ic_chat_active));
                imgProfile.setImageDrawable(getResources().getDrawable(R.drawable.ic_profile));
            }
        });
        imgEvents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(new EventFragment());
                imgHomes.setImageDrawable(getResources().getDrawable(R.drawable.ic_home_active));
                imgEvents.setImageDrawable(getResources().getDrawable(R.drawable.ic_event_klik));
                imgClubs.setImageDrawable(getResources().getDrawable(R.drawable.ic_community));
                imgChats.setImageDrawable(getResources().getDrawable(R.drawable.ic_chat));
                imgProfile.setImageDrawable(getResources().getDrawable(R.drawable.ic_profile));
            }
        });
        imgProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(new ProfileFragment());
                imgHomes.setImageDrawable(getResources().getDrawable(R.drawable.ic_home_active));
                imgEvents.setImageDrawable(getResources().getDrawable(R.drawable.ic_event));
                imgClubs.setImageDrawable(getResources().getDrawable(R.drawable.ic_community));
                imgChats.setImageDrawable(getResources().getDrawable(R.drawable.ic_chat));
                imgProfile.setImageDrawable(getResources().getDrawable(R.drawable.ic_profile_active));
            }
        });

    }

    private void setFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
    }

    @Override
    protected void onStart() {
        super.onStart();
        startSocket();
    }


    private void startSocket() {
        SocketSingleton.getInstance().getSocket().connect();
        SocketChatConnect socketChatConnect = new SocketChatConnect();
        socketChatConnect.setId(Session.init(context).getUser().getId());
        try {
            JSONObject jsonData = new JSONObject(socketChatConnect.toJson());
            SocketSingleton.getInstance().getSocket().emit(SocketSingleton.CHAT_START_CONNECTION,
                    jsonData
            );
        } catch (JSONException ignored) {
        }
    }
}
