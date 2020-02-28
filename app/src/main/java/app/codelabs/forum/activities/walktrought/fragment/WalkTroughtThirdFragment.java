package app.codelabs.forum.activities.walktrought.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import app.codelabs.forum.R;
import app.codelabs.forum.activities.login.LoginActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class WalkTroughtThirdFragment extends Fragment {
    private Button btnGetStarted;
    Context context;

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
        btnGetStarted = view.findViewById(R.id.btnnext_third);
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
