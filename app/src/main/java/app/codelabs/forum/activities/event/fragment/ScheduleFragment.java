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
import app.codelabs.forum.activities.event.DetailEventActivity;
import app.codelabs.forum.activities.event.adapter.ScheduleAdapter;
import app.codelabs.forum.helpers.ConnectionApi;
import app.codelabs.forum.models.ResponseSchedule;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ScheduleFragment extends Fragment {
    private RecyclerView recycleView;
    private ScheduleAdapter adapter;
    private Context context;

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
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        context = getActivity();
        setView(view);
        setRecycleView();
        getSchedule();
    }

    private void getSchedule() {
        ConnectionApi.apiService(context).getSchedule(((DetailEventActivity) getActivity()).data.getId()).enqueue(new Callback<ResponseSchedule>() {
            @Override
            public void onResponse(Call<ResponseSchedule> call, Response<ResponseSchedule> response) {
                if(response.body() != null){
                    if(response.isSuccessful() && response.body().getSuccess()){
                        adapter.setItems(response.body().getData());
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseSchedule> call, Throwable t) {
                if (t.getMessage() != null) {
                    Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setRecycleView() {
        recycleView.setHasFixedSize(true);
        recycleView.setLayoutManager(new LinearLayoutManager(context));
        recycleView.setAdapter(adapter);
    }

    private void setView(View view) {
        adapter = new ScheduleAdapter();
        recycleView = view.findViewById(R.id.rv_date_schedule);
    }

}
