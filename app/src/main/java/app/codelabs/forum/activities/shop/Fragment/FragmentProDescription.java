package app.codelabs.forum.activities.shop.Fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;

import app.codelabs.forum.R;
import app.codelabs.forum.models.ResponseListShopByCategories;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentProDescription extends Fragment {
    private Context context;
    private ResponseListShopByCategories.DataEntity data;
    private TextView tvDeskrip;
    public final static int DESKRIP = 0;
    public final static int PRODUCT_INFO = 1;

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
        loadData();

    }

    private void setView(View view) {
        tvDeskrip = view.findViewById(R.id.tv_Deskription);
    }

    private void loadData() {
        if (getArguments() != null) {
            int type = getArguments().getInt("type", 0);
            String strData = this.getArguments().getString("data");
            data =new Gson().fromJson(strData, ResponseListShopByCategories.DataEntity.class);
            if (type == DESKRIP) {
                tvDeskrip.setText(Html.fromHtml(data.getDescription()));
            } else if (type == PRODUCT_INFO) {
                geProductInfo();
            }
        }
    }

    private void geProductInfo() {

    }

    public FragmentProDescription setTypeAndData(int type, String data) {
        Bundle args = new Bundle();
        args.putInt("type", type);
        args.putString("data", data);
        setArguments(args);
        return this;
    }
}


