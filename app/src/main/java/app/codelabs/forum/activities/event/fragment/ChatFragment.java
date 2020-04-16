
package app.codelabs.forum.activities.event.fragment;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import app.codelabs.forum.R;
import app.codelabs.forum.activities.event.adapter.AdapterChat;
import app.codelabs.forum.models.Chat;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChatFragment extends Fragment {

    private RecyclerView recyclerView;
    private EditText etInputMessage;
    private ImageView ivImageSend;
    private Context context;

    private AdapterChat adapter;

    List<Chat> items = new ArrayList<>();

    String ownerName = "Aini";
    int ownerUserId = 1;

    String friendName = "Awenizar";
    int friendUserId = 2;


    public ChatFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chat, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //context = getApplicationContext();

        setView(view);
        setRecyclerView();
        setEvent();

        setSampleData();

    }

    private void setEvent() {
        ivImageSend.setOnClickListener(new View.OnClickListener() {
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

    private void setRecyclerView() {

        adapter = new AdapterChat();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapter);
    }

    private void setView(View view) {
        recyclerView =view.findViewById(R.id.recyclerView);
        etInputMessage =view.findViewById(R.id.et_send_Chat);
        ivImageSend =view.findViewById(R.id.img_Send_Chat);
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

}
