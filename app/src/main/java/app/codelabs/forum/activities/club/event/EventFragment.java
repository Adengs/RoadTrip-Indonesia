package app.codelabs.forum.activities.club.event;


import android.content.Context;
import android.content.Intent;
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
import app.codelabs.forum.activities.club.event.adapter.EventAdapter;
import app.codelabs.forum.activities.event.EventActivity;
import app.codelabs.forum.activities.login.ProgresDialogFragment;
import app.codelabs.forum.activities.club.event.bottom_sheet.BottomSheetJoinEvent;
import app.codelabs.forum.helpers.ConnectionApi;
import app.codelabs.forum.models.ResponsJoinEvent;
import app.codelabs.forum.models.ResponsListEventCommunity;
import app.codelabs.forum.models.ResponsUnjoinEvent;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventFragment extends Fragment {
    private RecyclerView recyclerView;
    private EventAdapter adapter;
    private BottomSheetJoinEvent bottomSheetJoinEvent = new BottomSheetJoinEvent();
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
        bottomSheetJoinEvent.setListener(new BottomSheetJoinEvent.Listener() {
            @Override
            public void onJoinClick(BottomSheetJoinEvent dialog, ResponsListEventCommunity.DataEntity data,int index) {
                joinEvent(dialog,data,index);
            }
        });
        adapter.setListener(new EventAdapter.OnItemSelection() {
            @Override
            public void onBtnJoin(ResponsListEventCommunity.DataEntity data,int selectionIndex) {
                if (data.getIs_join() == false) {
                    joinEventDialog(data,selectionIndex);
                } else {
                    unJoinEvent(data,selectionIndex);
                }

            }
        });
    }

    private void joinEventDialog(ResponsListEventCommunity.DataEntity data, int selectionIndex) {
        bottomSheetJoinEvent.setData(data,selectionIndex);
        bottomSheetJoinEvent.show(getChildFragmentManager(), "join_event");
    }

    private void joinEvent(final BottomSheetJoinEvent dialog, final ResponsListEventCommunity.DataEntity data, final int selectionIndex) {
        progresDialogFragment.show(getChildFragmentManager(), "progress");
        Map<String, String> dataJoin = new HashMap<>();
        dataJoin.put("event_id", String.valueOf(data.getId()));
        ConnectionApi.apiService(context).joinEvent(dataJoin).enqueue(new Callback<ResponsJoinEvent>() {
            @Override
            public void onResponse(Call<ResponsJoinEvent> call, Response<ResponsJoinEvent> response) {
                dialog.dismiss();
                progresDialogFragment.dismiss();
                if (response.isSuccessful() && response.body().getSuccess()) {
                    data.setIs_join(true);
                    adapter.setItemByIndex(data,selectionIndex);
                    Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context, EventActivity.class);
                    intent.putExtra("data", new Gson().toJson(data));
                    startActivity(intent);
                } else {
                    Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponsJoinEvent> call, Throwable t) {
                progresDialogFragment.dismiss();
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void unJoinEvent(final ResponsListEventCommunity.DataEntity data, final int selectionIndex) {
        Map<String, String> dataUnJoin = new HashMap<>();
        dataUnJoin.put("event_id", String.valueOf(data.getId()));
        progresDialogFragment.show(getChildFragmentManager(), "progress");
        ConnectionApi.apiService(context).unJoin(dataUnJoin).enqueue(new Callback<ResponsUnjoinEvent>() {
            @Override
            public void onResponse(Call<ResponsUnjoinEvent> call, Response<ResponsUnjoinEvent> response) {
                progresDialogFragment.dismiss();
                if (response.body() != null) {
                    if (response.isSuccessful() && response.body().getSuccess()) {
                        data.setIs_join(false);
                        adapter.setItemByIndex(data,selectionIndex);
                        Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();

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
