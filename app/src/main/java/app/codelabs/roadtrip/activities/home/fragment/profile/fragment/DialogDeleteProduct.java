package app.codelabs.roadtrip.activities.home.fragment.profile.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import org.greenrobot.eventbus.EventBus;

import app.codelabs.roadtrip.R;
import app.codelabs.roadtrip.models.ConfirmDelete;

public class DialogDeleteProduct extends DialogFragment {
    private RelativeLayout btnYes, btnNo;
    private Context context;

    public DialogDeleteProduct() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dialog_delete_product, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        context = getContext();

        setView(view);
        setEvent();
    }

    private void setEvent() {
        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().post(new ConfirmDelete());
                dismiss();
            }
        });
    }

    private void setView(View view) {
        btnYes = view.findViewById(R.id.btn_yes);
        btnNo = view.findViewById(R.id.btn_cancel);
    }

    @Override
    public void onStart() {
        super.onStart();
        getDialog().getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT);
    }
}