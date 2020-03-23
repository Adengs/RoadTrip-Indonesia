package app.codelabs.forum.activities.walktrought.fragment;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;

import app.codelabs.forum.R;
import app.codelabs.forum.activities.walktrought.WalkThroughActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class WalkTroughtFirstFragment extends Fragment {
    private TextView btnnext;

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

        setView(view);
        setEvent();

    }

    private void setView(View view) {
        btnnext = view.findViewById(R.id.txtnext);
    }

    private void setEvent() {
        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((WalkThroughActivity)getActivity()).viewPager.setCurrentItem(1);
            }
        });
    }

}
