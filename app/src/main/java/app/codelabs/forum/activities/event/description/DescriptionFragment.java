package app.codelabs.forum.activities.event.description;


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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import app.codelabs.forum.R;
import app.codelabs.forum.activities.menu_event.JoinPickFragment;
import app.codelabs.forum.models.ResponsListEventCommunity;

/**
 * A simple {@link Fragment} subclass.
 */
public class DescriptionFragment extends Fragment {
    private LinearLayout line_join;
    private String strData;
    private ResponsListEventCommunity.DataEntity data;
    private Boolean isjoind= false;
    private TextView tvTitle, tvStartEvent, tvEndEvent, tvLocation, tvDeskrip,tvDojoin,tvjoined;
    private ImageView ivImage;
    private JoinPickFragment joinPickFragment = new JoinPickFragment();
    private Context context;


    public DescriptionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_description, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        context = getContext();

        setView(view);
        getData();
       // setEvent();
        SetData();



    }

    private void SetData() {
        tvTitle.setText(Html.fromHtml(data.getTitle()));
        tvStartEvent.setText(data.getEvent_start());
        tvEndEvent.setText(data.getEvent_end());
        tvLocation.setText(data.getLocation());
        tvDeskrip.setText(Html.fromHtml(data.getDescription()));
        Picasso.with(context).load(data.getImage()).centerCrop().fit().into(ivImage);
    }

    /**
     *  private void setEvent() {
     *         if(data.getIs_join() == true){
     *             tvDojoin.setText("You are join this event");
     *             tvjoined.setText("Joined");
     *             tvjoined.setTextColor(Color.parseColor("#FFFFFF"));
     *             tvjoined.setBackgroundResource(R.drawable.shape_button_follow);
     *         }
     *         else {
     *             tvDojoin.setText("Do you want to join?");
     *             tvjoined.setText("Join");
     *             tvjoined.setTextColor(Color.parseColor("#F62C4C"));
     *             tvjoined.setBackgroundResource(R.drawable.shape_car_club);
     *         }
     *         tvjoined.setOnClickListener(new View.OnClickListener() {
     *             @Override
     *             public void onClick(View v) {
     *                 Fragment fragment = joinPickFragment;
     *                 Bundle ags = new Bundle();
     *                 ags.putString("data",(new Gson().toJson(data)));
     *                 fragment.setArguments(ags);
     *                 joinPickFragment.show(getFragmentManager(),"pick");
     *             }
     *         });
     *     }
     */


    private void getData() {
        Bundle bundle = this.getArguments();
        isjoind = bundle.getBoolean("is_join",false);
        strData = bundle.getString("data");
        data = new Gson().fromJson(strData, ResponsListEventCommunity.DataEntity.class);

    }

    private void setView(View view) {
        tvDojoin = view.findViewById(R.id.tvDojoin);
        tvjoined = view.findViewById(R.id.tvJoined);
        line_join = view.findViewById(R.id.liner_join);
        tvTitle = view.findViewById(R.id.tvTitle);
        tvStartEvent = view.findViewById(R.id.tvStartEvent);
        tvEndEvent = view.findViewById(R.id.tvEndEvent);
        tvLocation = view.findViewById(R.id.tvlocation);
        tvDeskrip = view.findViewById(R.id.tvDeskrip);
        ivImage = view.findViewById(R.id.ivImage);


    }
}
