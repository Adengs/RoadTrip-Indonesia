package app.codelabs.forum.activities.vote;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import app.codelabs.forum.R;
import app.codelabs.forum.activities.home.HomeActivity;

public class BottomSheetVote extends BottomSheetDialogFragment {
    private Button btnDoneVote;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_sheet_vote, container, false);
        return view;


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
                dismiss();
            }
        });

    }


    private void setView(View view) {
        btnDoneVote = view.findViewById(R.id.btn_done_Vote);
    }

}
