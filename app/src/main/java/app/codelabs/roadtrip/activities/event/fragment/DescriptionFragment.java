package app.codelabs.roadtrip.activities.event.fragment;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.gson.Gson;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.HashMap;
import java.util.Map;

import app.codelabs.roadtrip.activities.event.DetailEventActivity;
import app.codelabs.roadtrip.activities.event.adapter.ImageSliderAdapter;
import app.codelabs.roadtrip.activities.home.bottom_sheet.BottomSheetJoinEvent;
import app.codelabs.roadtrip.helpers.ConnectionApi;
import app.codelabs.roadtrip.models.ResponseListEventCommunity;
import app.codelabs.roadtrip.models.ResponseUnjoinEvent;
import app.codelabs.roadtrip.R;
import app.codelabs.roadtrip.activities.custom.ProgressDialogFragment;
import app.codelabs.roadtrip.models.ResponseJoinEvent;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DescriptionFragment extends Fragment {
    private TextView tvTitle, tvStartEvent, tvEndEvent, tvLocation, tvDescription, tvDoJoin, tvJoined;
    private BottomSheetJoinEvent bottomSheetJoinEvent = new BottomSheetJoinEvent();
    private ProgressDialogFragment progressDialogFragment = new ProgressDialogFragment();
    private Context context;
    private ImageSliderAdapter imageSliderAdapter = new ImageSliderAdapter();
    private SliderView cardSlider;

    private DetailEventActivity activity;

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
        activity = (DetailEventActivity) getActivity();

        setView(view);
        getData();
        setData();
        setEvent();
        setSliderView();

    }

    private void setData() {
        tvTitle.setText(Html.fromHtml(activity.data.getTitle()));
        tvStartEvent.setText(activity.data.getEvent_start());
        tvEndEvent.setText(activity.data.getEvent_end());
        tvLocation.setText(activity.data.getLocation());
        tvDescription.setText(Html.fromHtml(activity.data.getDescription()));

        if (activity.data.getIs_join() == true) {
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
                if (activity.data.getIs_join() == false) {
                    joinEventDialog(activity.data);
                } else {
                    unJoinEvent(activity.data);
                }
            }
        });

    }

    private void joinEventDialog(ResponseListEventCommunity.DataEntity data) {
        bottomSheetJoinEvent.setData(data, activity.selectedIndex);
        bottomSheetJoinEvent.show(getChildFragmentManager(), "join_event");
    }

    private void getData() {
        imageSliderAdapter.setItems(activity.data.getPhotos());
    }

    private void setSliderView() {
        cardSlider.setSliderAdapter(imageSliderAdapter);
        if (imageSliderAdapter.getCount() > 1) {
            cardSlider.startAutoCycle();
            cardSlider.setIndicatorAnimationDuration(600);
            cardSlider.setSliderAnimationDuration(600);
            cardSlider.setScrollTimeInSec(4);
        }
        cardSlider.setIndicatorAnimation(IndicatorAnimations.FILL);
        cardSlider.setSliderTransformAnimation(SliderAnimations.FADETRANSFORMATION);
    }

    private void setView(View view) {
        cardSlider = view.findViewById(R.id.imageSlider);
        tvDoJoin = view.findViewById(R.id.tvDojoin);
        tvJoined = view.findViewById(R.id.tvJoined);
        tvTitle = view.findViewById(R.id.tv_title);
        tvStartEvent = view.findViewById(R.id.tv_start_event);
        tvEndEvent = view.findViewById(R.id.tv_end_event);
        tvLocation = view.findViewById(R.id.tvlocation);
        tvDescription = view.findViewById(R.id.tvDeskrip);
    }

    private void unJoinEvent(final ResponseListEventCommunity.DataEntity data) {
        Map<String, String> dataUnJoin = new HashMap<>();
        dataUnJoin.put("event_id", String.valueOf(data.getId()));
        progressDialogFragment.show(getChildFragmentManager(), "progress");
        ConnectionApi.apiService(context).unJoin(dataUnJoin).enqueue(new Callback<ResponseUnjoinEvent>() {
            @Override
            public void onResponse(Call<ResponseUnjoinEvent> call, Response<ResponseUnjoinEvent> response) {
                progressDialogFragment.dismiss();
                if (response.body() != null) {
                    if (response.isSuccessful() && response.body().getSuccess()) {
                        Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        data.setIs_join(false);
                        activity.data = data;
                        activity.refreshJoin();
                        setData();
                        Intent intent = new Intent();
                        intent.putExtra("data", new Gson().toJson(data));
                        intent.putExtra("index", activity.selectedIndex);
                        getActivity().setResult(Activity.RESULT_OK, intent);
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseUnjoinEvent> call, Throwable t) {
                progressDialogFragment.dismiss();
                if (t.getMessage() != null) {
                    Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void joinEvent(final BottomSheetJoinEvent dialog, final ResponseListEventCommunity.DataEntity data, final int selectionIndex) {
        progressDialogFragment.show(getChildFragmentManager(), "progress");
        Map<String, String> dataJoin = new HashMap<>();
        dataJoin.put("event_id", String.valueOf(data.getId()));
        ConnectionApi.apiService(context).joinEvent(dataJoin).enqueue(new Callback<ResponseJoinEvent>() {
            @Override
            public void onResponse(Call<ResponseJoinEvent> call, Response<ResponseJoinEvent> response) {
                dialog.dismiss();
                progressDialogFragment.dismiss();
                if (response.isSuccessful() && response.body().getSuccess()) {
                    data.setIs_join(true);
                    activity.data = data;
                    activity.refreshJoin();
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
            public void onFailure(Call<ResponseJoinEvent> call, Throwable t) {
                progressDialogFragment.dismiss();
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}