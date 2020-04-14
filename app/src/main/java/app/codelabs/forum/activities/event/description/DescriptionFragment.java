package app.codelabs.forum.activities.event.description;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

import app.codelabs.forum.R;
import app.codelabs.forum.activities.event.EventActivity;
import app.codelabs.forum.activities.login.ProgresDialogFragment;
import app.codelabs.forum.activities.club.event.bottom_sheet.BottomSheetJoinEvent;
import app.codelabs.forum.helpers.ConnectionApi;
import app.codelabs.forum.models.ResponsJoinEvent;
import app.codelabs.forum.models.ResponseListEventCommunity;
import app.codelabs.forum.models.ResponsUnjoinEvent;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DescriptionFragment extends Fragment {
    private TextView tvTitle, tvStartEvent, tvEndEvent, tvLocation, tvDescription, tvDoJoin, tvJoined;
    private ImageView ivImage;
    private BottomSheetJoinEvent bottomSheetJoinEvent = new BottomSheetJoinEvent();
    private ProgresDialogFragment progresDialogFragment = new ProgresDialogFragment();
    private Context context;


    public DescriptionFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_description, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        context = getContext();

        setView(view);
        getData();
        setData();
        setEvent();

    }

    private void setData() {
        tvTitle.setText(Html.fromHtml(((EventActivity) getActivity()).data.getTitle()));
        tvStartEvent.setText(((EventActivity) getActivity()).data.getEvent_start());
        tvEndEvent.setText(((EventActivity) getActivity()).data.getEvent_end());
        tvLocation.setText(((EventActivity) getActivity()).data.getLocation());
        tvDescription.setText(Html.fromHtml(((EventActivity) getActivity()).data.getDescription()));
        Picasso.with(context).load(((EventActivity) getActivity()).data.getImage())
                .placeholder(R.drawable.default_image)
                .error(R.drawable.default_no_image)
                .fit().centerCrop().into(ivImage);

        if (((EventActivity) getActivity()).data.getIs_join() == true) {
            tvDoJoin.setText("Congratulation!");
            tvJoined.setText("Joined");
            tvJoined.setTextColor(Color.parseColor("#FFFFFF"));
            tvJoined.setBackgroundResource(R.drawable.shape_button_follow);
        } else {
            tvDoJoin.setText("Do you want to join?");
            tvJoined.setText("Join");
            tvJoined.setTextColor(Color.parseColor("#F62C4C"));
            tvJoined.setBackgroundResource(R.drawable.shape_car_club);
        }
    }

    private void setEvent() {
        bottomSheetJoinEvent.setListener(new BottomSheetJoinEvent.Listener() {
            @Override
            public void onJoinClick(BottomSheetJoinEvent dialog, ResponseListEventCommunity.DataEntity data, int index) {
                joinEvent(dialog, data, index);
            }
        });
        tvJoined.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((EventActivity) getActivity()).data.getIs_join() == false) {
                    joinEventDialog(((EventActivity) getActivity()).data);
                } else {
                    unJoinEvent(((EventActivity) getActivity()).data);
                }
            }
        });

    }

    private void joinEventDialog(ResponseListEventCommunity.DataEntity data) {
        bottomSheetJoinEvent.setData(data, ((EventActivity) getActivity()).selectedIndex);
        bottomSheetJoinEvent.show(getChildFragmentManager(), "join_event");
    }

    private void getData() {
    }

    private void setView(View view) {
        tvDoJoin = view.findViewById(R.id.tvDojoin);
        tvJoined = view.findViewById(R.id.tvJoined);
        tvTitle = view.findViewById(R.id.tv_title);
        tvStartEvent = view.findViewById(R.id.tv_start_event);
        tvEndEvent = view.findViewById(R.id.tv_end_event);
        tvLocation = view.findViewById(R.id.tvlocation);
        tvDescription = view.findViewById(R.id.tvDeskrip);
        ivImage = view.findViewById(R.id.ivImage);
    }

    private void unJoinEvent(final ResponseListEventCommunity.DataEntity data) {
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
                        data.setIs_join(false);
                        ((EventActivity) getActivity()).data = data;
                        setData();
                        Intent intent = new Intent();
                        intent.putExtra("data", new Gson().toJson(data));
                        intent.putExtra("index", ((EventActivity) getActivity()).selectedIndex);
                        getActivity().setResult(Activity.RESULT_OK, intent);
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

    private void joinEvent(final BottomSheetJoinEvent dialog, final ResponseListEventCommunity.DataEntity data, final int selectionIndex) {
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
                    ((EventActivity) getActivity()).data = data;
                    setData();
                    Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent();
                    intent.putExtra("data", new Gson().toJson(data));
                    intent.putExtra("index", selectionIndex);
                    getActivity().setResult(Activity.RESULT_OK, intent);
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
}