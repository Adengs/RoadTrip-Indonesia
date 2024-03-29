package app.codelabs.roadtrip.activities.shop.fragment;

import android.content.Context;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import app.codelabs.roadtrip.R;
import app.codelabs.roadtrip.models.ResponseDetailShopItem;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentProDescription extends Fragment {
    private Context context;
    private ResponseDetailShopItem.Data data;
    private TextView tvDeskrip;
    //public final static int DESKRIP = 0;
   // public final static int PRODUCT_INFO = 1;

    public FragmentProDescription() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pro_description, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setView(view);
        getData();
        setData();

    }

    private void setData() {
        tvDeskrip.setText(Html.fromHtml(data.getDescription()));
    }

    private void getData() {
        String strData = this.getArguments().getString("data");
        data =new Gson().fromJson(strData, ResponseDetailShopItem.Data.class);

    }

    private void setView(View view) {
        tvDeskrip = view.findViewById(R.id.tv_desc);
    }

}


