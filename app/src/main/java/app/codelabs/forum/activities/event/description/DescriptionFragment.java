package app.codelabs.forum.activities.event.description;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import app.codelabs.forum.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DescriptionFragment extends Fragment {
    private LinearLayout line_join;
    private Boolean isjoind= false;


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

        setView(view);

        isjoind = this.getArguments().getBoolean("is_join", true);

        if(isjoind==true){
           line_join.setVisibility(View.VISIBLE);
        }
        else {
            line_join.setVisibility(View.GONE);
        }

    }

    private void setView(View view) {
        line_join = view.findViewById(R.id.liner_join);

    }
}
