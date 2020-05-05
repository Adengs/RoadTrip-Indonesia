package app.codelabs.forum.activities.conversation;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.nkzawa.emitter.Emitter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

import app.codelabs.forum.R;
import app.codelabs.forum.activities.conversation.adapter.ChatAdapter;
import app.codelabs.forum.helpers.ConnectionApi;
import app.codelabs.forum.helpers.Session;
import app.codelabs.forum.helpers.SocketSingleton;
import app.codelabs.forum.models.ResponseChatRoomList;
import app.codelabs.forum.models.ResponseListChatInRoom;
import app.codelabs.forum.models.ResponseRoomChatDetail;
import app.codelabs.forum.models.SocketChatSendMessageToRoom;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChatRoomActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private EditText etInputMessage;
    private TextView tvTitle;
    private ProgressBar progressBar;
    private ImageView btnBack;
    private FloatingActionButton btnSend;
    private Context context;

    private ChatAdapter adapter;


    private ResponseChatRoomList.DataEntity room;
    private ResponseRoomChatDetail.DataEntity roomDetail;
    private int limit = 10;
    private int currentOffset = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getApplicationContext();
        setContentView(R.layout.activity_chat_room);

        setView();
        setRecyclerView();
        setEvent();
        getData();
        getChatRoomDetail();
        setSocketIncomingMessage();

    }

    private void setSocketIncomingMessage() {
        SocketSingleton.getInstance().getSocket().off(SocketSingleton.ON_ROOM_INCOMING_MESSAGE);
        SocketSingleton.getInstance().getSocket().on(SocketSingleton.ON_ROOM_INCOMING_MESSAGE, new Emitter.Listener() {
            @Override
            public void call(final Object... args) {
                JSONObject json = (JSONObject) args[0];
                final SocketChatSendMessageToRoom data = SocketChatSendMessageToRoom.get(json.toString());
                if (data.getRoom_id() == room.getRoom().getId()) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            pushMessage(data);
                        }
                    });
                }
            }
        });
    }

    private void pushMessage(SocketChatSendMessageToRoom data) {
        adapter.addItem(data);
    }

    private void getData() {
        String strJson = getIntent().getStringExtra("data");
        room = ResponseChatRoomList.DataEntity.get(strJson);
        adapter.setOwnerId(Session.init(context).getUser().getId());
        tvTitle.setText(room.getRoom().getTitle());
    }

    private void getChatRoomDetail() {
        ConnectionApi.apiService(context).getRoomDetail(room.getRoom().getId()).enqueue(new Callback<ResponseRoomChatDetail>() {
            @Override
            public void onResponse(Call<ResponseRoomChatDetail> call, Response<ResponseRoomChatDetail> response) {
                if (response.body() != null) {
                    if (response.isSuccessful() && response.body().getStatus() == 200) {
                        roomDetail = response.body().getData();
                        adapter.setRoomMember(roomDetail.getMembers());
                        getListChatInRoom(limit, 0);
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseRoomChatDetail> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                if (t.getMessage() != null) {
                    Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void getListChatInRoom(int limit, final int offset) {
        currentOffset = offset;
        ConnectionApi.apiService(context).getListChatInRoom(room.getRoom().getId(), limit, offset).enqueue(new Callback<ResponseListChatInRoom>() {
            @Override
            public void onResponse(Call<ResponseListChatInRoom> call, Response<ResponseListChatInRoom> response) {
                progressBar.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
                if (response.body() != null) {
                    if (response.isSuccessful() && response.body().getStatus() == 200) {
                        if (offset == 0) {
                            adapter.setItems(response.body().getData());
                        } else {
                            adapter.addAllItems(response.body().getData());
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseListChatInRoom> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                if (t.getMessage() != null) {
                    Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setView() {
        recyclerView = findViewById(R.id.recyclerview_message);
        etInputMessage = findViewById(R.id.et_input_message);
        btnSend = findViewById(R.id.btn_send);
        btnBack = findViewById(R.id.iv_back);
        tvTitle = findViewById(R.id.tv_title);
        progressBar = findViewById(R.id.progressbar);
    }

    private void setRecyclerView() {
        adapter = new ChatAdapter();
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setReverseLayout(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

    }

    private void setEvent() {
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (room == null) {
                    return;
                }
                SocketChatSendMessageToRoom socketChatSendMessageToRoom = new SocketChatSendMessageToRoom();
                socketChatSendMessageToRoom.setAuthor_id(Session.init(context).getUser().getId());
                socketChatSendMessageToRoom.setAuthor_name(Session.init(context).getUser().getName());
                socketChatSendMessageToRoom.setRoom_id(room.getRoom().getId());
                socketChatSendMessageToRoom.setContent(etInputMessage.getText().toString());
                socketChatSendMessageToRoom.setTime(new Date().getTime());
                try {
                    JSONObject jsonData = new JSONObject(socketChatSendMessageToRoom.toJson());
                    SocketSingleton.getInstance().getSocket().emit(SocketSingleton.CHAT_SEND_MESSAGE,
                            jsonData
                    );
                    etInputMessage.setText("");
                } catch (JSONException ignored) {
                }
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SocketSingleton.getInstance().getSocket().off(SocketSingleton.ON_ROOM_INCOMING_MESSAGE);
    }
}
