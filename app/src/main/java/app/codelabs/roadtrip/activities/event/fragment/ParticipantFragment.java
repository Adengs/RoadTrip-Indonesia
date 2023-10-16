package app.codelabs.roadtrip.activities.event.fragment;


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
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import app.codelabs.roadtrip.activities.event.DetailEventActivity;
import app.codelabs.roadtrip.activities.event.adapter.AdapterParticipant;
import app.codelabs.roadtrip.helpers.ConnectionApi;
import app.codelabs.roadtrip.models.EventBusClass;
import app.codelabs.roadtrip.models.ResponseParticipantEvent;
import app.codelabs.roadtrip.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ParticipantFragment extends Fragment {
    private RecyclerView recyclerView;
    private TextView tvMessage;
    private AdapterParticipant adapter;
    private Context context;
    private DetailEventActivity activity;

    public ParticipantFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_participant, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        context = getContext();
        activity = (DetailEventActivity) getActivity();

        setView(view);
        setRecycleView();
        setIsJoin();
    }

    private void setIsJoin() {
        if (!activity.data.isIs_join()) {
            tvMessage.setText("Anda belum bergabung di event ini.");
            tvMessage.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        } else {
            tvMessage.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            loadData();
        }
    }


    private void loadData() {
        ConnectionApi.apiService(context).eventParticipant(activity.data.getId()).enqueue(new Callback<ResponseParticipantEvent>() {
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
        adapter = new AdapterParticipant();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapter);
    }

    private void setView(View view) {
        recyclerView = view.findViewById(R.id.recyclerView);
        tvMessage = view.findViewById(R.id.tv_message);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onJoinChange(EventBusClass.EventJoin data){
        setIsJoin();
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }
}
