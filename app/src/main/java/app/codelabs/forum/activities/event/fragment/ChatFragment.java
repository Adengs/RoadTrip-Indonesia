
package app.codelabs.forum.activities.event.fragment;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.nkzawa.emitter.Emitter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONException;
import org.json.JSONObject;

import app.codelabs.forum.R;
import app.codelabs.forum.activities.conversation.adapter.ChatAdapter;
import app.codelabs.forum.activities.event.DetailEventActivity;
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

public class ChatFragment extends Fragment {
    private Context context;
    private DetailEventActivity activity;

    private RecyclerView recyclerView;
    private EditText etInputMessage;
    private ProgressBar progressBar;
    private FloatingActionButton btnSend;

    private ChatAdapter adapter;

    private ResponseRoomChatDetail.DataEntity roomDetail;
    private int limit = 10;
    private int currentOffset = 0;

    public ChatFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_chat, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        context = getActivity();
        activity = (DetailEventActivity) getActivity();

        setView(view);
        setRecyclerView();
        setEvent();
        setIfJoin();
    }

    private void setIfJoin() {
        if(activity.data.isIs_join()) {
            getData();
            getChatRoomDetail();
            setSocketIncomingMessage();
        }
    }

    private void setEvent() {
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (roomDetail == null && !activity.data.isIs_join()) {
                    return;
                }
                SocketChatSendMessageToRoom socketChatSendMessageToRoom = new SocketChatSendMessageToRoom();
                socketChatSendMessageToRoom.setAuthor_id(Session.init(context).getUser().getId());
                socketChatSendMessageToRoom.setRoom_id(activity.data.getRoomId());
                socketChatSendMessageToRoom.setContent(etInputMessage.getText().toString());
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
    }

    private void setRecyclerView() {
        adapter = new ChatAdapter();
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setReverseLayout(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    private void setView(View view) {
        recyclerView = view.findViewById(R.id.recyclerview_message);
        etInputMessage = view.findViewById(R.id.et_input_message);
        btnSend = view.findViewById(R.id.btn_send);
        progressBar = view.findViewById(R.id.progressbar);
    }

    private void getData() {
        adapter.setOwnerId(Session.init(context).getUser().getId());
    }

    private void getChatRoomDetail() {
        ConnectionApi.apiService(context).getRoomDetail(activity.data.getRoomId()).enqueue(new Callback<ResponseRoomChatDetail>() {
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
        ConnectionApi.apiService(context).getListChatInRoom(activity.data.getRoomId(), limit, offset).enqueue(new Callback<ResponseListChatInRoom>() {
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


    private void setSocketIncomingMessage() {
        SocketSingleton.getInstance().getSocket().off(SocketSingleton.ON_ROOM_INCOMING_MESSAGE);
        SocketSingleton.getInstance().getSocket().on(SocketSingleton.ON_ROOM_INCOMING_MESSAGE, new Emitter.Listener() {
            @Override
            public void call(final Object... args) {
                JSONObject json = (JSONObject) args[0];
                final SocketChatSendMessageToRoom data = SocketChatSendMessageToRoom.get(json.toString());
                if (data.getRoom_id() == activity.data.getRoomId()) {
                    activity.runOnUiThread(new Runnable() {
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

    @Override
    public void onStop() {
        super.onStop();
        SocketSingleton.getInstance().getSocket().off(SocketSingleton.ON_ROOM_INCOMING_MESSAGE);
    }
}
