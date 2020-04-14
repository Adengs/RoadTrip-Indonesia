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

import java.util.List;

import app.codelabs.forum.R;
import app.codelabs.forum.activities.event.adapter.AdapterMyEvent;
import app.codelabs.forum.helpers.ConnectionApi;
import app.codelabs.forum.models.ResponsMyEvent;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyEventFragment extends Fragment {
    private RecyclerView recyclerView;
    private AdapterMyEvent adapter;
    private Context context;

    public MyEventFragment() {
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

        setView(view);
        setRecycleView();
        loadData();
    }

    private void loadData() {
        ConnectionApi.apiService(context).myEvent().enqueue(new Callback<ResponsMyEvent>(){

            @Override
            public void onResponse(Call<ResponsMyEvent> call, Response<ResponsMyEvent> response) {
                if (response.body() != null) {
                    if (response.isSuccessful() && response.body().getSuccess()) {
                        adapter.setItems(response.body().getData());
                    }

                }
            }

            @Override
            public void onFailure(Call<ResponsMyEvent> call, Throwable t) {
                if (t.getMessage() != null) {
                    Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setView(View view) {
        adapter = new AdapterMyEvent();
        recyclerView = view.findViewById(R.id.recyclerView);
    }

    private void setRecycleView() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapter);
    }


}
