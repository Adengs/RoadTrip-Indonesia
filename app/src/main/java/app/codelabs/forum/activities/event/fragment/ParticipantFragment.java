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
import android.widget.Toast;

import app.codelabs.forum.R;
import app.codelabs.forum.activities.event.EventActivity;
import app.codelabs.forum.activities.event.adapter.AdapterParticipant;
import app.codelabs.forum.helpers.ConnectionApi;
<<<<<<< HEAD:app/src/main/java/app/codelabs/forum/activities/event/fragment/ParticipantFragment.java
import app.codelabs.forum.models.ResponsParticipantEvent;
=======
import app.codelabs.forum.models.ResponseParticipantEvent;
>>>>>>> ca3b14d1cd9f4e8a67cc5df78a81f3d2823de0bc:app/src/main/java/app/codelabs/forum/activities/event/participant/ParticipantFragment.java
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ParticipantFragment extends Fragment {
    private RecyclerView recyclerView;
    private AdapterParticipant adapter;
    private Context context;

    public ParticipantFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_participant, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view , @Nullable Bundle savedInstanceState){
        super.onViewCreated(view , savedInstanceState);
        context = getContext();

        setView(view);
        setRecycleView();
        getData();
        loadData();
    }

    private void getData() {

    }

    private void loadData() {
        ConnectionApi.apiService(context).eventParticipant(((EventActivity) getActivity()).data.getId()).enqueue(new Callback<ResponseParticipantEvent>() {
            @Override
            public void onResponse(Call<ResponseParticipantEvent> call, Response<ResponseParticipantEvent> response) {
                if (response.body() != null) {
                    if (response.isSuccessful() && response.body().getSuccess()) {
                        adapter.setItems(response.body().getData());
                    }

                }
            }

            @Override
            public void onFailure(Call<ResponseParticipantEvent> call, Throwable t) {
                if (t.getMessage() != null) {
                    Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void setRecycleView() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapter);
    }

    private void setView(View view) {
        adapter = new AdapterParticipant();
        recyclerView = view.findViewById(R.id.recyclerView);
    }



}
