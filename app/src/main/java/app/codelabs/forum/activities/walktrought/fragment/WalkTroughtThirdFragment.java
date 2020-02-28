package app.codelabs.forum.activities.walktrought.fragment;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import app.codelabs.forum.R;
import app.codelabs.forum.activities.walktrought.WalkThroughActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class WalkTroughtThirdFragment extends Fragment {


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

        setView(view);
        setEvent();


    }

    private void setView(View view) {

    }

    private void setEvent() {

    }
}
