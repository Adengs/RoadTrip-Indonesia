package app.codelabs.forum.activities.vote;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import app.codelabs.forum.R;
import app.codelabs.forum.activities.home.HomeActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentBottomSheetVote extends Fragment {
    Button btnDoneVote;


    public FragmentBottomSheetVote() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment_bottom_sheet_vote, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setView(view);
        setEvent();
    }

    private void setEvent() {
        btnDoneVote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), HomeActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
    }

    private void setView(View view) {
        btnDoneVote=view.findViewById(R.id.btn_done_Vote);
    }
}
