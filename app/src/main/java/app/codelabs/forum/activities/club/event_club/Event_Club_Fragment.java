package app.codelabs.forum.activities.club.event_club;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import app.codelabs.forum.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Event_Club_Fragment extends Fragment {
    RecyclerView recyclerView;
    private EventClubAdapter adapter;
    Context context;


    public Event_Club_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_event_club, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setView(view);
        setRecycleView();
    }

    private void setRecycleView() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapter);
    }

    private void setView(View view) {
        adapter = new EventClubAdapter();
        recyclerView = view.findViewById(R.id.rv_event);
    }
}
