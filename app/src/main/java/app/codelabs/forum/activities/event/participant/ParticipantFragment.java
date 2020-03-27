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
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.List;

import app.codelabs.forum.R;
import app.codelabs.forum.helpers.ConnectionApi;
import app.codelabs.forum.helpers.Session;
import app.codelabs.forum.models.ResponsListEventCommunity;
import app.codelabs.forum.models.ResponsParticipantEvent;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class ParticipantFragment extends Fragment {
    RecyclerView recyclerView;
    private AdapterParticipant adapter;
    Context context;
    private String token;
    private String apptoken;
    private Session session;
    private Integer id;
    ResponsListEventCommunity.DataEntity datas = new ResponsListEventCommunity.DataEntity();



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
        session = Session.init(context);
        apptoken = session.getAppToken();
        token = session.getToken();

        Bundle bundle = this.getArguments();
        id = bundle.getInt("event_id",0);
        setView(view);
        setRecycleView();
        loadData();
    }

    private void loadData() {
        ConnectionApi.apiService().partisipanevent(id,token,apptoken).enqueue(new Callback<ResponsParticipantEvent>() {

            @Override
            public void onResponse(Call<ResponsParticipantEvent> call, Response<ResponsParticipantEvent> response) {
                if (response.isSuccessful() && response.body().getSuccess()){

                    adapter.setItems(response.body().getData());
                    adapter.addItems(response.body().getData());
                }
                else {

                    Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ResponsParticipantEvent> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
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
        recyclerView = view.findViewById(R.id.recyclerviewparticipant);
    }



}
