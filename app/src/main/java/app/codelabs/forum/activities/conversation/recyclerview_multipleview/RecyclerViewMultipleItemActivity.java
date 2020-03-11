package app.codelabs.forum.activities.conversation.recyclerview_multipleview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import app.codelabs.forum.R;
import app.codelabs.forum.activities.conversation.adapter.ChatAdapter;
import app.codelabs.forum.activities.conversation.models.Chat;
import app.codelabs.forum.activities.home.HomeActivity;

public class RecyclerViewMultipleItemActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    EditText etInputMessage;
    ImageView btnSend,btnBack;
    Context context;


    ChatAdapter adapter;

    List<Chat> items = new ArrayList<>();
    List<Chat> name = new ArrayList<>();

    String ownerName = "Nurul Maulida";
    int ownerUserId = 1;

    String friendName = "Zikra Hanum";
    int friendUserId = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_multiple_item);
        context = getApplicationContext();

        setView();
        setRecyclerView();
        setEvent();

        setSampleData();

    }


    private void setSampleData() {

        adapter.setOwnerId(ownerUserId);

        items.add(new Chat(ownerName, "Hay, how are you? ", new Date().toString(), friendUserId));
        items.add(new Chat(friendName, "I'm good :)?", new Date().toString(), ownerUserId));
        items.add(new Chat(ownerName, "I wanna riding with you! Please :(", new Date().toString(), friendUserId));
        items.add(new Chat(ownerName, "Tonight?", new Date().toString(),ownerUserId));
        items.add(new Chat(friendName, "Ok. 7 pm i'll go your apartment", new Date().toString(), ownerUserId));

        adapter.setItems(items);
    }

    private void setView() {
        recyclerView = findViewById(R.id.recyclerview_message);
        etInputMessage = findViewById(R.id.et_input_message);
        btnSend = findViewById(R.id.btn_send);
        btnBack = findViewById(R.id.btnpanah);
    }

    private void setRecyclerView() {
        adapter = new ChatAdapter();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapter);

    }

    private void setEvent() {
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                items.add(new Chat(
                        ownerName,
                        etInputMessage.getText().toString(),
                        new Date().toString(),
                        ownerUserId
                ));
                adapter.setItems(items);

                etInputMessage.setText("");
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RecyclerViewMultipleItemActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
    }

}
