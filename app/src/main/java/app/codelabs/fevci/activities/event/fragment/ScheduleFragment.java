package app.codelabs.fevci.activities.event.fragment;


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

import app.codelabs.fevci.activities.event.DetailEventActivity;
import app.codelabs.fevci.activities.event.adapter.ScheduleAdapter;
import app.codelabs.fevci.helpers.ConnectionApi;
import app.codelabs.fevci.models.EventBusClass;
import app.codelabs.fevci.models.ResponseSchedule;
import app.codelabs.forum.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ScheduleFragment extends Fragment {
    private TextView tvMessage;
    private RecyclerView recycleView;
    private ScheduleAdapter adapter;
    private Context context;
    private DetailEventActivity activity;

    public ScheduleFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_schedule, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        context = getActivity();
        activity = (DetailEventActivity) getActivity();

        setView(view);
        setRecycleView();
        setIsJoin();
    }

    private void setIsJoin() {
        if (!activity.data.isIs_join()) {
            tvMessage.setText("Anda belum bergabung di event ini.");
            tvMessage.setVisibility(View.VISIBLE);
        } else {
            tvMessage.setVisibility(View.GONE);
            getSchedule();
        }
    }

    private void getSchedule() {
        ConnectionApi.apiService(context).getSchedule(activity.data.getId()).enqueue(new Callback<ResponseSchedule>() {
            @Override
            public void onResponse(Call<ResponseSchedule> call, Response<ResponseSchedule> response) {
                if (response.body() != null) {
                    if (response.isSuccessful() && response.body().getSuccess()) {
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
        adapter = new ScheduleAdapter();
        recycleView.setHasFixedSize(true);
        recycleView.setLayoutManager(new LinearLayoutManager(context));
        recycleView.setAdapter(adapter);
    }

    private void setView(View view) {
        tvMessage = view.findViewById(R.id.tv_message);
        recycleView = view.findViewById(R.id.recyclerview);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onJoinChange(EventBusClass.EventJoin data) {
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
