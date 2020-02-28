package app.codelabs.forum.activities.walktrought.fragment;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import app.codelabs.forum.R;
import app.codelabs.forum.activities.walktrought.WalkThroughActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class WalkTroughtSecondFragment extends Fragment {
TextView BtnNext;

    public WalkTroughtSecondFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_walk_trought_second, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setView(view);
        setEvent();


    }

    private void setView(View view) {
        BtnNext =view.findViewById(R.id.btnnext_second);
    }

    private void setEvent() {
        BtnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((WalkThroughActivity)getActivity()).viewPager.setCurrentItem(2);
            }
        });
    }

}
