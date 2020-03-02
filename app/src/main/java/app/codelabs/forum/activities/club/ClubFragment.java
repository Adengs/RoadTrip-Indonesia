package app.codelabs.forum.activities.club;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import app.codelabs.forum.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ClubFragment extends Fragment {


    public ClubFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_club2, container, false);
    }

}
