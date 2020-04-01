package app.codelabs.forum.activities.walktrought.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.squareup.picasso.Picasso;

import app.codelabs.forum.R;
import app.codelabs.forum.activities.login.LoginActivity;
import app.codelabs.forum.helpers.Session;
import app.codelabs.forum.models.ResponWalkThrough;

/**
 * A simple {@link Fragment} subclass.
 */
public class WalkTroughtThirdFragment extends Fragment {
    private Button btnGetStarted;
    private TextView tvTitle, tvDesc;
    private ImageView ivBackground;
    private Context context;

    public WalkTroughtThirdFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_walk_trought_third, container, false);
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
        ResponWalkThrough.DataEntity item = Session.init(context).getWalkTrough().get(2);
        tvTitle.setText(item.getTitle());
        tvDesc.setText(item.getDescription());
        Picasso.with(context).load(item.getImage()).fit().centerCrop().into(ivBackground);
    }


    private void setView(View view) {
        btnGetStarted = view.findViewById(R.id.btn_get_started);
        tvTitle = view.findViewById(R.id.tv_title);
        tvDesc = view.findViewById(R.id.tv_desc);
        ivBackground = view.findViewById(R.id.iv_background);
    }

    private void setEvent() {
        btnGetStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplication(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
