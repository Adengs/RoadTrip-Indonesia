package app.codelabs.forum.activities.shop.fragment;

import android.content.Context;
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
<<<<<<< HEAD:app/src/main/java/app/codelabs/forum/activities/shop/fragment/FragmentRincian.java

import app.codelabs.forum.activities.shop.adapter.AdapterShop;
import app.codelabs.forum.models.ResponsListShopByCategories;
=======
import app.codelabs.forum.activities.shop.Adapter.AdapterShop;
import app.codelabs.forum.models.ResponseListShopByCategories;
>>>>>>> ca3b14d1cd9f4e8a67cc5df78a81f3d2823de0bc:app/src/main/java/app/codelabs/forum/activities/shop/Fragment/FragmentRincian.java

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentRincian extends Fragment {
    Context context;
    private TextView tvProductName, tvLocation, tvPrice,tvCategories;
    private ImageView ivImage;
    private TabLayout tabLayout;
    private ResponseListShopByCategories.DataEntity data;
    private  ViewPager viewPagerShop;



    public FragmentRincian() {
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
        setViewPager();

    }

    private void setData() {
        tvProductName.setText(Html.fromHtml(data.getName()));
        tvLocation.setText(Html.fromHtml(data.getLocation()));
        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        tvPrice.setText(formatRupiah.format((double)data.getPrice()));
        tvCategories.setText(Html.fromHtml((data.getCategory())));
        Picasso.with(context).load(data.getPhoto()).fit().centerCrop().into(ivImage);
    }

    private void getData() {
        if (this.getArguments().getString("data") !=null){
            String strData = this.getArguments().getString("data");
            data = new Gson().fromJson(strData, ResponseListShopByCategories.DataEntity.class);
        }

    }


    private void setView(View view) {
        tvLocation = view.findViewById(R.id.tv_Location);
        tvPrice = view.findViewById(R.id.tv_Price);
        tvCategories = view.findViewById(R.id.tv_Category);
        tvProductName = view.findViewById(R.id.tv_Product_Name);
        ivImage = view.findViewById(R.id.iv_Image);
        viewPagerShop = view.findViewById(R.id.viewPager);
        tabLayout = view.findViewById(R.id.tab_layout);
    }

    private void setViewPager(){
        AdapterShop adapterShop = new AdapterShop(getChildFragmentManager());
        adapterShop.addFragment(new FragmentProDescription().
                setTypeAndData(FragmentProDescription.DESKRIP, new Gson().toJson(data)), "Description");
        adapterShop.addFragment(new FragmentProDescription().
                setTypeAndData(FragmentProDescription.PRODUCT_INFO,new Gson().toJson(data)), "Product Info");

        viewPagerShop.setAdapter(adapterShop);

        tabLayout.setupWithViewPager(viewPagerShop);
    }

}
