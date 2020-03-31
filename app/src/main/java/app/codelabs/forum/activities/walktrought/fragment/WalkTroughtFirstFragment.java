package app.codelabs.forum.activities.walktrought.fragment;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import app.codelabs.forum.R;
import app.codelabs.forum.activities.walktrought.WalkThroughActivity;
import app.codelabs.forum.models.ResponWalkThrough;

/**
 * A simple {@link Fragment} subclass.
 */
public class WalkTroughtFirstFragment extends Fragment {
    private TextView tvnext,tvTitle,tvDes,tvid;
    private ImageView ivWalkTrought;
    public String data;
    public ResponWalkThrough.DataEntity datas;
    Context context;

    public WalkTroughtFirstFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_walk_trought_first, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        context = getContext();

        setView(view);
        setEvent();

        Gson gson = new Gson();
        Bundle bundle = this.getArguments();
        data = bundle.getString("data");
        datas = gson.fromJson(data, ResponWalkThrough.DataEntity.class);
        loadData();

    }

    private void loadData() {
        if(datas.getId()==1){
            tvTitle.setText(datas.getTitle());
            tvDes.setText(datas.getDescription());
            Picasso.with(context).load(datas.getImage()).centerCrop().fit().into(ivWalkTrought);
        }


    }

    private void setView(View view) {
        tvnext = view.findViewById(R.id.tvnext);
        tvTitle = view.findViewById(R.id.tvtitlefirst);
        tvDes = view.findViewById(R.id.tvdescfirst);
        ivWalkTrought = view.findViewById(R.id.img_fist);
    }

    private void setEvent() {
        tvnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((WalkThroughActivity)getActivity()).viewPager.setCurrentItem(1);
            }
        });
    }

}
