package app.codelabs.forum.activities.shop.Fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import app.codelabs.forum.R;
import app.codelabs.forum.activities.shop.Adapter.AdapterShop;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentProInfo extends Fragment {

    private AdapterShop adapterShop;
    Context context;

    public FragmentProInfo() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pro_info, container, false);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setView(view);
        setEvent();


    }

    private void setView(View view) {
       // adapterShop = new AdapterShop();
    }

    private void setEvent() {
    }


}
