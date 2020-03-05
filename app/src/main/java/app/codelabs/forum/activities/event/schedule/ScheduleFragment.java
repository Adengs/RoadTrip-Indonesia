package app.codelabs.forum.activities.event.schedule;


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

import app.codelabs.forum.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ScheduleFragment extends Fragment {
RecyclerView recycleView;
private AdapterSchedule adapter;
Context context;

    public ScheduleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_schedule, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view , @Nullable Bundle savedInstanceState){
        super.onViewCreated(view , savedInstanceState);
        setView(view);
        setRecycleView();

    }

    private void setRecycleView() {
        recycleView.setHasFixedSize(true);
        recycleView.setLayoutManager(new LinearLayoutManager(context));
        recycleView.setAdapter(adapter);
    }

    private void setView(View view) {
        adapter= new AdapterSchedule();
        recycleView=view.findViewById(R.id.recyclerviewshedule);

    }

}
