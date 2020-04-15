package app.codelabs.forum.activities.community.event;


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
import app.codelabs.forum.activities.community.event.adapter.EventAdapter;
import app.codelabs.forum.activities.event.EventActivity;
import app.codelabs.forum.activities.login.ProgresDialogFragment;
import app.codelabs.forum.activities.club.event.bottom_sheet.BottomSheetJoinEvent;
import app.codelabs.forum.helpers.ConnectionApi;
import app.codelabs.forum.models.ResponseJoinEvent;
import app.codelabs.forum.models.ResponseListEventCommunity;
import app.codelabs.forum.models.ResponseUnjoinEvent;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;

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
        adapter = new EventAdapter(this);

        setView(view);
        setEvent();
        setRecycleView();
        loadData();
    }

    private void setEvent() {
        bottomSheetJoinEvent.setListener(new BottomSheetJoinEvent.Listener() {
            @Override
            public void onJoinClick(BottomSheetJoinEvent dialog, ResponseListEventCommunity.DataEntity data, int index) {
                joinEvent(dialog, data, index);
            }
        });
        adapter.setListener(new EventAdapter.OnItemSelection() {
            @Override
            public void onBtnJoin(ResponseListEventCommunity.DataEntity data, int selectionIndex) {
                if (data.getIs_join() == false) {
                    joinEventDialog(data, selectionIndex);
                } else {
                    unJoinEvent(data, selectionIndex);
                }

            }
        });
    }

    private void joinEventDialog(ResponseListEventCommunity.DataEntity data, int selectionIndex) {
        bottomSheetJoinEvent.setData(data, selectionIndex);
        bottomSheetJoinEvent.show(getChildFragmentManager(), "join_event");
    }

    private void joinEvent(final BottomSheetJoinEvent dialog, final ResponseListEventCommunity.DataEntity data, final int selectionIndex) {
        progresDialogFragment.show(getChildFragmentManager(), "progress");
        Map<String, String> dataJoin = new HashMap<>();
        dataJoin.put("event_id", String.valueOf(data.getId()));
        ConnectionApi.apiService(context).joinEvent(dataJoin).enqueue(new Callback<ResponseJoinEvent>() {
            @Override
            public void onResponse(Call<ResponseJoinEvent> call, Response<ResponseJoinEvent> response) {
                dialog.dismiss();
                progresDialogFragment.dismiss();
                if (response.isSuccessful() && response.body().getSuccess()) {
                    data.setIs_join(true);
                    adapter.setItemByIndex(data, selectionIndex);
                    Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context, EventActivity.class);
                    intent.putExtra("data", new Gson().toJson(data));
                    intent.putExtra("index", selectionIndex);
                    startActivityForResult(intent, EventActivity.REQ_REFRESH_EVENT);
                } else {
                    Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseJoinEvent> call, Throwable t) {
                progresDialogFragment.dismiss();
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void unJoinEvent(final ResponseListEventCommunity.DataEntity data, final int selectionIndex) {
        Map<String, String> dataUnJoin = new HashMap<>();
        dataUnJoin.put("event_id", String.valueOf(data.getId()));
        progresDialogFragment.show(getChildFragmentManager(), "progress");
        ConnectionApi.apiService(context).unJoin(dataUnJoin).enqueue(new Callback<ResponseUnjoinEvent>() {
            @Override
            public void onResponse(Call<ResponseUnjoinEvent> call, Response<ResponseUnjoinEvent> response) {
                progresDialogFragment.dismiss();
                if (response.body() != null) {
                    if (response.isSuccessful() && response.body().getSuccess()) {
                        data.setIs_join(false);
                        adapter.setItemByIndex(data, selectionIndex);
                        Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();

                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseUnjoinEvent> call, Throwable t) {
                progresDialogFragment.dismiss();
                if (t.getMessage() != null) {
                    Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void loadData() {
        ConnectionApi.apiService(context).getListEvent().enqueue(new Callback<ResponseListEventCommunity>() {
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
        recyclerView = view.findViewById(R.id.rv_event);
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
