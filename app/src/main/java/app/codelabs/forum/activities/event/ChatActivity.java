package app.codelabs.forum.activities.event;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import app.codelabs.forum.R;
import app.codelabs.forum.activities.event.chat.AdapterChat;
import app.codelabs.forum.models.Chat;

public class ChatActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    EditText etInputMessage;
    ImageView sendChat;
    Context context;

    AdapterChat adapter;

    List<Chat> items = new ArrayList<>();

    String ownerName = "Aini";
    int ownerUserId = 1;

    String friendName = "Awenizar";
    int friendUserId = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        context = getApplicationContext();

        setView();
        setRecyclerView();
        setEvent();

        setSampleData();

    }

       private void setSampleData() {
        adapter.setOwnerId(ownerUserId);

        items.add(new Chat(ownerName, "Hello World", new Date().toString(), ownerUserId));
        items.add(new Chat(friendName, "Pue Haba?", new Date().toString(), friendUserId));
        items.add(new Chat(ownerName, "Haba geut.., pajan balek gampong?", new Date().toString(), ownerUserId));
        items.add(new Chat(ownerName, "Lon na reuncana jak u banda?", new Date().toString(), ownerUserId));
        items.add(new Chat(friendName, "Buleun uke lon balek, beuk tuwo jeumpot lon bak terminal", new Date().toString(), friendUserId));

        adapter.setItems(items);
    }

    private void setView() {
        recyclerView = findViewById(R.id.recyclerviewpesan);
        etInputMessage = findViewById(R.id.et_sendChat);
        sendChat = findViewById(R.id.imgSendChat);
    }

    private void setRecyclerView() {

        adapter = new AdapterChat();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapter);
    }

    private void setEvent() {
        sendChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                items.add(new Chat(
                        ownerName,
                        etInputMessage.getText().toString(),
                        new Date().toString(),
                        ownerUserId
                )) ;
                adapter.setItems(items);

                etInputMessage.setText("");
            }
        });
    }
}
