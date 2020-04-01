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

import com.squareup.picasso.Picasso;

import app.codelabs.forum.R;
import app.codelabs.forum.activities.walktrought.WalkThroughActivity;
import app.codelabs.forum.helpers.Session;
import app.codelabs.forum.models.ResponWalkThrough;

/**
 * A simple {@link Fragment} subclass.
 */
public class WalkTroughtFirstFragment extends Fragment {
    private TextView tvNext, tvTitle, tvDesc;
    private ImageView ivBackground;
    private Context context;

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
        setData();
    }

    private void setData() {
        ResponWalkThrough.DataEntity item = Session.init(context).getWalkTrough().get(0);
        tvTitle.setText(item.getTitle());
        tvDesc.setText(item.getDescription());
        Picasso.with(context).load(item.getImage()).fit().centerCrop().into(ivBackground);
    }

    private void setView(View view) {
        tvNext = view.findViewById(R.id.tv_next);
        tvTitle = view.findViewById(R.id.tv_title);
        tvDesc = view.findViewById(R.id.tv_desc);
        ivBackground = view.findViewById(R.id.iv_background);
    }

    private void setEvent() {
        tvNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((WalkThroughActivity) getActivity()).viewPager.setCurrentItem(1);
            }
        });
    }

}
