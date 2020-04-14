package app.codelabs.forum.activities.event.fragment;


import android.content.Context;
import android.content.Intent;
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

import com.google.gson.Gson;

import app.codelabs.forum.R;
import app.codelabs.forum.activities.event.EventActivity;
import app.codelabs.forum.activities.event.adapter.EventAdapter;
import app.codelabs.forum.helpers.ConnectionApi;
import app.codelabs.forum.models.ResponseListEventCommunity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;

public class EventFragment extends Fragment {
    private RecyclerView recyclerView;
    private EventAdapter adapter;
    private Context context;

    public EventFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_event, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        context = getContext();
        adapter = new EventAdapter(this);

        setView(view);
        setRecycleView();
        loadData();
    }

    private void loadData() {
        ConnectionApi.apiService(context).getListMyEvent().enqueue(new Callback<ResponseListEventCommunity>(){

            @Override
            public void onResponse(Call<ResponseListEventCommunity> call, Response<ResponseListEventCommunity> response) {
                if (response.body() != null) {
                    if (response.isSuccessful() && response.body().getSuccess()) {
                        adapter.setItems(response.body().getData());
                    }

                }
            }

            @Override
            public void onFailure(Call<ResponseListEventCommunity> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void setView(View view) {
        recyclerView = view.findViewById(R.id.recyclerviewevent);
    }

    private void setRecycleView() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_OK && requestCode == EventActivity.REQ_REFRESH_EVENT) {
            int index = data.getIntExtra("index", -1);
            ResponseListEventCommunity.DataEntity resultData = new Gson().fromJson(data.getStringExtra("data"), ResponseListEventCommunity.DataEntity.class);

            if (index != -1) {
                adapter.setItemByIndex(resultData, index);
            }
        }
    }
}
