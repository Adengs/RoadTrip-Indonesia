package app.codelabs.forum.activities.menu_event;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import app.codelabs.forum.R;
import app.codelabs.forum.activities.event.EventActivity;
import app.codelabs.forum.activities.event.description.DescriptionFragment;
import app.codelabs.forum.activities.home.HomeActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class JoinPickFragment extends BottomSheetDialogFragment {
    Button btnJoin, btnCancel;
    Context context;


    public JoinPickFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_join_pick, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setView(view);
        setEvent();
        context = getContext();
    }

    private void setEvent() {
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MenuEventActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
        btnJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), EventActivity.class);
                intent.putExtra("is_join",true);
                startActivity(intent);
            }
        });
    }

    private void setView(View view) {
        btnCancel = view.findViewById(R.id.btn_cancel);
        btnJoin = view.findViewById(R.id.btn_join);

    }
}
