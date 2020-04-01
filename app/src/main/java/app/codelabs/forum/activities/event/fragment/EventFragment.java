package app.codelabs.forum.activities.event.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import app.codelabs.forum.R;
import app.codelabs.forum.activities.event.EventActivity;
import app.codelabs.forum.activities.event.adapter.AdapterEventCardView;
import app.codelabs.forum.activities.profile.SettingProfile;
import app.codelabs.forum.helpers.ConnectionApi;
import app.codelabs.forum.helpers.Session;
import app.codelabs.forum.models.ResponsListMemberCompany;
import app.codelabs.forum.models.ResponsMyEvent;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class EventFragment extends Fragment {
    RecyclerView recyclerView;
    private AdapterEventCardView adapter;
    Context context;
    private Session session;//definisi variabel session dengan tipe data session
    private String token;
    private String apptoken;




    public EventFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_event, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        context = getContext();
        session = Session.init(context);
        apptoken = session.getAppToken();
        token = session.getToken();


        setView(view);
        setRecycleView();
        loadData();
    }

    private void loadData() {
        ConnectionApi.apiService().Myevent(token,apptoken).enqueue(new Callback<ResponsMyEvent>(){

            @Override
            public void onResponse(Call<ResponsMyEvent> call, Response<ResponsMyEvent> response) {
                if (response.isSuccessful() && response.body().getSuccess()){

                    adapter.setItems(response.body().getData());
                }
                else {

                    Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponsMyEvent> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void setView(View view) {
        adapter = new AdapterEventCardView();
        recyclerView = view.findViewById(R.id.recyclerviewevent);
    }

    private void setRecycleView() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapter);
    }


}
