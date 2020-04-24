package app.codelabs.forum.activities.home.fragment.chat;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import app.codelabs.forum.R;
import app.codelabs.forum.activities.conversation.ChatRoomActivity;
import app.codelabs.forum.activities.conversation.adapter.ConversationAdapter;
import app.codelabs.forum.helpers.ConnectionApi;
import app.codelabs.forum.helpers.Session;
import app.codelabs.forum.helpers.SocketSingleton;
import app.codelabs.forum.models.ResponseChatRoomList;
import app.codelabs.forum.models.SocketChatConnect;
import app.codelabs.forum.models.SocketChatJoinRoom;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConversationFragment extends Fragment {
    private RecyclerView recyclerView;
    private ConversationAdapter adapter;
    private Context context;

    public ConversationFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_conversation, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        context = getActivity();
        setView(view);
        setEvent();
        setRecycleView();
        getChatRoomList();
    }

    private void setView(View view) {
        adapter = new ConversationAdapter();
        recyclerView = view.findViewById(R.id.recyclerview);
    }

    private void setEvent() {
        adapter.setListener(new ConversationAdapter.Listener() {
            @Override
            public void onRoomClick(ResponseChatRoomList.DataEntity item) {
                Intent intent = new Intent(context, ChatRoomActivity.class);
                intent.putExtra("data", item.toJson());
                startActivity(intent);
            }
        });
    }

    private void setRecycleView() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapter);
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


    private void getChatRoomList() {
        ConnectionApi.apiService(context).getRoomList(Session.init(context).getUser().getId()).enqueue(new Callback<ResponseChatRoomList>() {
            @Override
            public void onResponse(Call<ResponseChatRoomList> call, Response<ResponseChatRoomList> response) {
                if (response.body() != null) {
                    if (response.isSuccessful() && response.body().getStatus() == 200) {
                        adapter.setItems(response.body().getData());
                        socketChatJoinRoom(response.body().getData());
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseChatRoomList> call, Throwable t) {
                if (t.getMessage() != null) {
                    Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void socketChatJoinRoom(List<ResponseChatRoomList.DataEntity> items) {
        for (ResponseChatRoomList.DataEntity item : items) {
            SocketChatJoinRoom socketChatJoinRoom = new SocketChatJoinRoom();
            socketChatJoinRoom.setAuthor_id(Session.init(context).getUser().getId());
            socketChatJoinRoom.setRoom_id(item.getRoom().getId());
            try {
                JSONObject jsonData = new JSONObject(socketChatJoinRoom.toJson());
                SocketSingleton.getInstance().getSocket().emit(SocketSingleton.CHAT_JOIN_ROOM,
                        jsonData
                );
            } catch (JSONException ignored) {
            }
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        startSocket();
    }

    @Override
    public void onStop() {
        super.onStop();
//        SocketSingleton.getInstance().getSocket().disconnect();
    }
}
