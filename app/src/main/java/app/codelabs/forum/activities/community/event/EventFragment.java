package app.codelabs.forum.activities.community.event;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import app.codelabs.forum.R;
import app.codelabs.forum.activities.login.ProgresDialogFragment;
import app.codelabs.forum.activities.menu_event.JoinPickFragment;
import app.codelabs.forum.helpers.ConnectionApi;
import app.codelabs.forum.models.ResponsListEventCommunity;
import app.codelabs.forum.models.ResponsUnjoinEvent;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventFragment extends Fragment {
    private RecyclerView recyclerView;
    private EventAdapter adapter;
    private JoinPickFragment joinPickFragment = new JoinPickFragment();
    private ProgresDialogFragment progresDialogFragment = new ProgresDialogFragment();
    private Context context;


    public EventFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_event_club, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        context = getContext();

        setView(view);
        setEvent();
        setRecycleView();
        loadData();
    }

    private void setEvent() {
        adapter.setListener(new EventAdapter.OnItemSelection() {
            @Override
            public void onBtnJoin(ResponsListEventCommunity.DataEntity data) {
                if (data.getIs_join() == false) {
                    Fragment fragment = joinPickFragment;
                    Bundle ags = new Bundle();
                    ags.putString("data", (new Gson().toJson(data)));
                    fragment.setArguments(ags);
                    joinPickFragment.show(getChildFragmentManager(), "pick");
                } else {
                    unJoin(data);
                }

            }
        });
    }

    private void unJoin(ResponsListEventCommunity.DataEntity data) {
        Map<String, String> dataUnJoin = new HashMap<>();
        dataUnJoin.put("event_id", String.valueOf(data.getId()));
        progresDialogFragment.show(getChildFragmentManager(), "progress");
        ConnectionApi.apiService(context).unJoin(dataUnJoin).enqueue(new Callback<ResponsUnjoinEvent>() {
            @Override
            public void onResponse(Call<ResponsUnjoinEvent> call, Response<ResponsUnjoinEvent> response) {
                progresDialogFragment.dismiss();
                if (response.body() != null) {
                    if (response.isSuccessful() && response.body().getSuccess()) {
                        Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        loadData();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponsUnjoinEvent> call, Throwable t) {
                progresDialogFragment.dismiss();
                if (t.getMessage() != null) {
                    Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void loadData() {
        ConnectionApi.apiService(context).listEvent().enqueue(new Callback<ResponsListEventCommunity>() {
            @Override
            public void onResponse(Call<ResponsListEventCommunity> call, Response<ResponsListEventCommunity> response) {
                if (response.body() != null) {
                    if (response.isSuccessful() && response.body().getSuccess()) {
                        adapter.setItems(response.body().getData());
                    }
                }

            }

            @Override
            public void onFailure(Call<ResponsListEventCommunity> call, Throwable t) {
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
        adapter = new EventAdapter();
        recyclerView = view.findViewById(R.id.rv_event);
    }
}
