package app.codelabs.forum.activities.club.event.bottom_sheet;


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
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import app.codelabs.forum.R;
import app.codelabs.forum.activities.event.EventActivity;
import app.codelabs.forum.activities.login.ProgresDialogFragment;
import app.codelabs.forum.helpers.ConnectionApi;
import app.codelabs.forum.models.ResponsJoinEvent;
import app.codelabs.forum.models.ResponsListEventCommunity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BottomSheetJoinEvent extends BottomSheetDialogFragment {
    private Button btnJoin, btnCancel;
    private Context context;
    private ResponsListEventCommunity.DataEntity data;
    private Listener listener;
    private int selectionIndex = -1;

    public BottomSheetJoinEvent() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.bottom_sheet_join_event, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        context = getContext();

        setView(view);
        setEvent();

    }

    private void setEvent() {
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        btnJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                joinEvent();
            }
        });

    }

    private void joinEvent() {
        if (listener != null && selectionIndex != -1) {
            listener.onJoinClick(this, data, selectionIndex);
        }
    }

    private void setView(View view) {
        btnCancel = view.findViewById(R.id.btn_cancel);
        btnJoin = view.findViewById(R.id.btn_join);
    }

    public void setData(ResponsListEventCommunity.DataEntity data, int selectionIndex) {
        this.data = data;
        this.selectionIndex = selectionIndex;
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    public interface Listener {
        void onJoinClick(BottomSheetJoinEvent dialog, ResponsListEventCommunity.DataEntity data, int selectionIndex);
    }
}
