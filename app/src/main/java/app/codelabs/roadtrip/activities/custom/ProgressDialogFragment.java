package app.codelabs.roadtrip.activities.custom;


import android.os.Bundle;

import androidx.fragment.app.DialogFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import app.codelabs.roadtrip.R;

public class ProgressDialogFragment extends DialogFragment {



    public ProgressDialogFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setCancelable(false);
        return inflater.inflate(R.layout.fragment_progres_dialog, container, false);
    }

}
