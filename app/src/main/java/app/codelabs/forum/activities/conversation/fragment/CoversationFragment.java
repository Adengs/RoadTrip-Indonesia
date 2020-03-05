package app.codelabs.forum.activities.conversation.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import app.codelabs.forum.R;
import app.codelabs.forum.activities.conversation.adapter.ConversationAdapter;
import app.codelabs.forum.activities.conversation.recyclerview_multipleview.RecyclerViewMultipleItemActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class CoversationFragment extends Fragment {
    RecyclerView recyclerView;
    private ConversationAdapter adapter;
    Context context;
    ImageView btnEvent;




    public CoversationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_coversation, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setView(view);
        setEvent();
        setRecycleView();

    }




    private void setView(View view) {
        adapter = new ConversationAdapter();
        btnEvent = view.findViewById(R.id.btnevent);
        recyclerView = view.findViewById(R.id.recyclerviewConv);
    }

    private void setEvent() {
        btnEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), RecyclerViewMultipleItemActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
    }

    private void setRecycleView() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapter);
    }
}
