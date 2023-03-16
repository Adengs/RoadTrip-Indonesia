package app.codelabs.fevci.activities.event;

import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import app.codelabs.fevci.helpers.Session;
import app.codelabs.fevci.helpers.SocketSingleton;
import app.codelabs.fevci.models.EventBusClass;
import app.codelabs.fevci.models.ResponseDoBookmark;
import app.codelabs.fevci.models.ResponseEventDetail;
import app.codelabs.fevci.models.SocketChatJoinRoom;
import app.codelabs.forum.R;
import app.codelabs.fevci.activities.custom.ProgressDialogFragment;
import app.codelabs.fevci.activities.event.adapter.EventPagerAdapter;
import app.codelabs.fevci.activities.event.fragment.ChatFragment;
import app.codelabs.fevci.activities.event.fragment.DescriptionFragment;
import app.codelabs.fevci.activities.event.fragment.ParticipantFragment;
import app.codelabs.fevci.activities.event.fragment.ScheduleFragment;
import app.codelabs.fevci.activities.event.fragment.WalkieTalkieFragment;
import app.codelabs.fevci.helpers.ConnectionApi;
import app.codelabs.fevci.models.ResponseBookmarkEvent;
import app.codelabs.fevci.models.ResponseListEventCommunity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailEventActivity extends AppCompatActivity {
    public static final int REQ_REFRESH_EVENT = 1001;
    private Context context;
    private TabLayout tabLayoutEvent;
    private ViewPager viewPagerEvent;
    public ResponseListEventCommunity.DataEntity data;
    private Toolbar toolbar;
    public int selectedIndex = -1;

    private EventPagerAdapter adapter;
    private ProgressDialogFragment progressDialogFragment = new ProgressDialogFragment();
    private Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        context = getApplicationContext();
        setView();
        getData();
        setToolbar();

        if (getIntent().getStringExtra("data") != null) {
            progressDialogFragment.show(getSupportFragmentManager(), "detail-event");
            setViewPager();
            getBookmark();
        }
    }

    private void getData() {
        if (getIntent().getStringExtra("data") != null) {
            String strData = getIntent().getStringExtra("data");
            selectedIndex = getIntent().getIntExtra("index", -1);
            data = new Gson().fromJson(strData, ResponseListEventCommunity.DataEntity.class);
        } else {
            progressDialogFragment.show(getSupportFragmentManager(), "detail-event");

            int eventId = getIntent().getIntExtra("event_id", 0);
            ConnectionApi.apiService(context).getEvent(eventId).enqueue(new Callback<ResponseEventDetail>() {
                @Override
                public void onResponse(Call<ResponseEventDetail> call, Response<ResponseEventDetail> response) {
                    if (response.body() != null) {
                        if (response.isSuccessful() && response.body().isSuccess()) {
                            data = response.body().getData();
                            setViewPager();
                            getBookmark();
                        } else {
                            progressDialogFragment.dismiss();
                            Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }

                @Override
                public void onFailure(Call<ResponseEventDetail> call, Throwable t) {
                    progressDialogFragment.dismiss();
                    if (t.getMessage() != null) {
                        Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
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
        adapter.addFragment(new ParticipantFragment(), "Participant");
        adapter.addFragment(new ScheduleFragment(), "Schedule");
        adapter.addFragment(new WalkieTalkieFragment(), "Walkie Talkie");
        adapter.addFragment(new ChatFragment(), "Chat");
        viewPagerEvent.setAdapter(adapter);

        tabLayoutEvent.setupWithViewPager(viewPagerEvent);

        joinRoom();
    }

    private void joinRoom() {
        if (data.isIs_join()) {
            SocketChatJoinRoom socketChatJoinRoom = new SocketChatJoinRoom();
            socketChatJoinRoom.setAuthor_id(Session.init(context).getUser().getId());
            socketChatJoinRoom.setRoom_id(data.getRoomId());
            try {
                JSONObject jsonData = new JSONObject(socketChatJoinRoom.toJson());
                SocketSingleton.getInstance().getSocket().emit(SocketSingleton.CHAT_JOIN_ROOM,
                        jsonData
                );
            } catch (JSONException ignored) {
            }
        }
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
        if (item.getItemId() == R.id.menu_bookmark) {
            bookmarkEvent();
        }
        return super.onOptionsItemSelected(item);
    }

    private void bookmarkEvent() {
        progressDialogFragment.show(getSupportFragmentManager(), "detail-event");
        ConnectionApi.apiService(context).doBookmark(data.getId(), "event").enqueue(new Callback<ResponseDoBookmark>() {
            @Override
            public void onResponse(Call<ResponseDoBookmark> call, Response<ResponseDoBookmark> response) {
                if (response.body() != null) {
                    if (response.isSuccessful() && response.body().getSuccess()) {
                        getBookmark();
                    }
                } else {
                    progressDialogFragment.dismiss();
                }

            }

            @Override
            public void onFailure(Call<ResponseDoBookmark> call, Throwable t) {
                progressDialogFragment.dismiss();
                if (t.getMessage() != null) {
                    Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void getBookmark() {
        ConnectionApi.apiService(context).getBookmarkEvent().enqueue(new Callback<ResponseBookmarkEvent>() {
            @Override
            public void onResponse(Call<ResponseBookmarkEvent> call, Response<ResponseBookmarkEvent> response) {
                progressDialogFragment.dismiss();
                if (response.body() != null) {
                    if (response.isSuccessful()) {
                        if (response.body().getSuccess()) {
                            checkBookmark(response.body().getData());
                        } else {
                            checkBookmark(new ArrayList());
                        }
                    }
                }

            }

            @Override
            public void onFailure(Call<ResponseBookmarkEvent> call, Throwable t) {
                progressDialogFragment.dismiss();
                if (t.getMessage() != null) {
                    Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void checkBookmark(List<ResponseListEventCommunity.DataEntity> items) {
        data.setBookmark(false);
        for (ResponseListEventCommunity.DataEntity item : items) {
            if (item.getId() == data.getId()) {
                data.setBookmark(true);
                break;
            }
        }

        refreshToolbar();
    }

    private void refreshToolbar() {
        if (menu != null) {
            if (data.isBookmark()) {
                menu.getItem(0).setIcon(ContextCompat.getDrawable(this, R.drawable.ic_bookmark_fill));
            } else {
                menu.getItem(0).setIcon(ContextCompat.getDrawable(this, R.drawable.ic_bookmark));
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.menu_bookmark, menu);
        this.menu = menu;
        return true;
    }

    public void refreshJoin() {
        EventBus.getDefault().post(new EventBusClass.EventJoin());
    }
}
