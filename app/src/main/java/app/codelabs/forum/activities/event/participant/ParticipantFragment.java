package app.codelabs.forum.activities.event.participant;


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

import com.google.gson.Gson;

import app.codelabs.forum.R;
import app.codelabs.forum.helpers.ConnectionApi;
import app.codelabs.forum.models.ResponseListEventCommunity;
import app.codelabs.forum.models.ResponseParticipantEvent;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class ParticipantFragment extends Fragment {
    private RecyclerView recyclerView;
    private AdapterParticipant adapter;
    private Context context;
    private String strData;
    private ResponseListEventCommunity.DataEntity data ;



    public ParticipantFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
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
        Bundle bundle = this.getArguments();
        strData = bundle.getString("data");
        data = new Gson().fromJson(strData, ResponseListEventCommunity.DataEntity.class);
    }

    private void loadData() {
        ConnectionApi.apiService(context).eventParticipant(data.getId()).enqueue(new Callback<ResponseParticipantEvent>() {

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
