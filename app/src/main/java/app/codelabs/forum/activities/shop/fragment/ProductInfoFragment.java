package app.codelabs.forum.activities.shop.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import app.codelabs.forum.R;
import app.codelabs.forum.models.ResponseDetailShopItem;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductInfoFragment extends Fragment {
    private TextView tvBerat, tvKondisi, tvPemesananMin, tvKategori;
    private ResponseDetailShopItem.DataEntity data;

    public ProductInfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_product_info, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setView(view);
        getData();
        setData();
    }

    private void setData() {
        tvKondisi.setText(data.getKondisi());
        tvBerat.setText(data.getBerat());
        tvPemesananMin.setText(data.getMin_pesanan());
        tvKategori.setText(data.getCategory().getCategory());
    }

    private void getData() {
        String strData = this.getArguments().getString("data");
        data =new Gson().fromJson(strData, ResponseDetailShopItem.DataEntity.class);

    }

    private void setView(View view) {
        tvBerat = view.findViewById(R.id.tv_berat);
        tvKondisi = view.findViewById(R.id.tv_kondisi);
        tvKategori = view.findViewById(R.id.tv_category);
        tvPemesananMin = view.findViewById(R.id.tv_pemesenanmin);
    }
}
