package app.codelabs.forum.activities.shop.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import app.codelabs.forum.R;
import app.codelabs.forum.activities.shop.DetailProductActivity;
import app.codelabs.forum.activities.shop.adapter.AdapterShopDeskription;
import app.codelabs.forum.models.ResponseDetailShopItem;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentDetailShop extends Fragment {
    Context context;
    private TextView tvProductName, tvLocation, tvPrice, tvCategories, tvMaps,tvStore;
    private ImageView ivImage,ivLogo;
    private TabLayout tabLayout;
    private ResponseDetailShopItem.DataEntity data;
    private ViewPager viewPagerShop;


    public FragmentDetailShop() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_rincian, container, false);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        context = getContext();

        getData();
        setView(view);
        setData();
        setEvent();
        setViewPager();

    }

    private void setEvent() {
        tvMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setMaps();
            }
        });
    }

    private void setMaps() {
        String url_map = "https://www.google.com/maps/search/?api=1&query=";
        float zoomLevel = 16.0f; //This goes up to 21
        Uri gmmIntentUri = Uri.parse(url_map+ data.getStore().getLatitude()+","+data.getStore().getLongitude());
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        //mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }

    private void setData() {
        tvProductName.setText(Html.fromHtml(data.getName()));
        tvLocation.setText(Html.fromHtml(data.getStore().getLocation()));
        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        tvPrice.setText(formatRupiah.format((double) data.getPrice()));
        tvCategories.setText(Html.fromHtml((data.getCategory().getCategory())));
        tvStore.setText(Html.fromHtml(data.getStore().getName()));
        Picasso.with(context).load(data.getStore().getLogo()).fit().centerCrop().into(ivLogo);
        Picasso.with(context).load(data.getPhoto()).fit().centerCrop().into(ivImage);
    }

    private void getData() {
        data = ((DetailProductActivity) getActivity()).data;
    }


    private void setView(View view) {
        tvLocation = view.findViewById(R.id.tv_Location);
        tvPrice = view.findViewById(R.id.tv_Price);
        tvCategories = view.findViewById(R.id.tv_Category);
        tvProductName = view.findViewById(R.id.tv_Product_Name);
        ivImage = view.findViewById(R.id.iv_Image);
        viewPagerShop = view.findViewById(R.id.viewPager);
        tabLayout = view.findViewById(R.id.tab_layout);
        tvMaps = view.findViewById(R.id.tv_map);
        ivLogo = view.findViewById(R.id.iv_logo);
        tvStore = view.findViewById(R.id.tv_store);
    }

    private void setViewPager() {
        AdapterShopDeskription adapterShop = new AdapterShopDeskription(getChildFragmentManager());
        adapterShop.addFragment(new FragmentProDescription().
                setTypeAndData(FragmentProDescription.DESKRIP, new Gson().toJson(data)), "Description");
        adapterShop.addFragment(new FragmentProDescription().
                setTypeAndData(FragmentProDescription.PRODUCT_INFO, new Gson().toJson(data)), "Product Info");

        viewPagerShop.setAdapter(adapterShop);

        tabLayout.setupWithViewPager(viewPagerShop);
    }

}
