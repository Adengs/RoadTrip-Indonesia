package app.codelabs.roadtrip.activities.shop.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;
import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import app.codelabs.roadtrip.activities.shop.DetailProductActivity;
import app.codelabs.roadtrip.activities.shop.adapter.AdapterShopDeskription;
import app.codelabs.roadtrip.R;
import app.codelabs.roadtrip.activities.event.adapter.ImageSliderAdapter;
import app.codelabs.roadtrip.helpers.ConnectionApi;
import app.codelabs.roadtrip.models.ResponseDetailShopItem;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentDetailShop extends Fragment {
    Context context;
    private TextView tvProductName, tvLocation, tvPrice, tvCategories, tvMaps, tvStore;
    private ImageView ivImage, ivLogo;
    private TabLayout tabLayout;
    private ResponseDetailShopItem.Data data;
    private ImageSliderAdapter imageSliderAdapter = new ImageSliderAdapter();
    private List<String> list = new ArrayList<>();
    private List<String> list2 = new ArrayList<>();
    private SliderView cardSlider;
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
//        setSliderView();

    }

    private void setSliderView() {
        cardSlider.setSliderAdapter(imageSliderAdapter);
        if (imageSliderAdapter.getCount() > 1) {
            cardSlider.startAutoCycle();
            cardSlider.setIndicatorAnimationDuration(600);
            cardSlider.setSliderAnimationDuration(600);
            cardSlider.setScrollTimeInSec(4);
        }
        cardSlider.setIndicatorAnimation(IndicatorAnimations.FILL);
        cardSlider.setSliderTransformAnimation(SliderAnimations.FADETRANSFORMATION);
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
        Uri gmmIntentUri = Uri.parse(url_map + data.getStore().getLatitude() + "," + data.getStore().getLongitude());
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
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

//        if (list != null){
//            for (int i = 0; i < list.size(); i++) {
//                list.remove(i);
//            }
//        }

//        if (list != null ) {
//            for (int i = 0; i < data.getPhoto().size(); i++) {
//                Log.e("CEK SIZE IMAGE 1", "setData: " + list.size() );
//                list.removeAll(Collections.singleton(i));
////                list.remove(i);
//                list.add(data.getPhoto().get(i).getImage());
//                imageSliderAdapter.setItems(list);
//
//                Log.e("CEK SIZE IMAGE 2", "setData: " + list.size() );
//            }
//        }
    }

    private void getData() {
        data = ((DetailProductActivity) getActivity()).data;
//        String strData = getActivity().getIntent().getStringExtra("data");
//        data = new Gson().fromJson(strData, ResponseDetailShopItem.Data.class);

        for (int i = 0; i < data.getPhoto().size(); i++) {
            list.add(data.getPhoto().get(i).getImage());
            imageSliderAdapter.setItems(list);
        }

        ConnectionApi.apiService(context).getShopDetailItem(data.getId()).enqueue(new Callback<ResponseDetailShopItem>() {
            @Override
            public void onResponse(Call<ResponseDetailShopItem> call, Response<ResponseDetailShopItem> response) {
                if (response.body() != null) {
                    if (response.isSuccessful() && response.body().getSuccess()) {
                        data = response.body().getData();
                        setData();
                        setData();
                        setEvent();
                        setViewPager();
                        setSliderView();

                        for (int i = 0; i < data.getPhoto().size(); i++) {
                            list2.add(data.getPhoto().get(i).getImage());
                            imageSliderAdapter.setItems(list2);
                        }

//                        for (int i = 0; i < data.getPhoto().size(); i++) {
//                            Log.e("CEK SIZE IMAGE 1", "setData: " + list.size() );
//                            list.removeAll(Collections.singleton(i));
////                list.remove(i);
//                            list.add(data.getPhoto().get(i).getImage());
//                            imageSliderAdapter.setItems(list);
//
//                            Log.e("CEK SIZE IMAGE 2", "setData: " + list.size() );
//                        }
                    }

                }
            }

            @Override
            public void onFailure(Call<ResponseDetailShopItem> call, Throwable t) {
                if (t.getMessage() != null) {
                    Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        });

    }


    private void setView(View view) {
        tvLocation = view.findViewById(R.id.tv_Location);
        tvPrice = view.findViewById(R.id.tv_Price);
        tvCategories = view.findViewById(R.id.tv_Category);
        tvProductName = view.findViewById(R.id.tv_Product_Name);
        cardSlider = view.findViewById(R.id.imageSlider);
        viewPagerShop = view.findViewById(R.id.viewPager);
        tabLayout = view.findViewById(R.id.tab_layout);
        tvMaps = view.findViewById(R.id.tv_map);
        ivLogo = view.findViewById(R.id.iv_logo);
        tvStore = view.findViewById(R.id.tv_store);
    }

    private void setViewPager() {
        AdapterShopDeskription adapterShop = new AdapterShopDeskription(getChildFragmentManager());
        FragmentProDescription fragmentProDescription = new FragmentProDescription();
        ProductInfoFragment productInfoFragment = new ProductInfoFragment();
        Bundle args = new Bundle();
        args.putString("data", new Gson().toJson(data));
        fragmentProDescription.setArguments(args);
        productInfoFragment.setArguments(args);
        adapterShop.addFragment(fragmentProDescription,"Description");
        adapterShop.addFragment(productInfoFragment, "Product Info");
        viewPagerShop.setAdapter(adapterShop);
        tabLayout.setupWithViewPager(viewPagerShop);
    }

}
