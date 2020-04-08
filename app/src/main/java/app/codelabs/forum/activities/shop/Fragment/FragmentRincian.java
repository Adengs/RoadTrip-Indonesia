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
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import androidx.viewpager.widget.ViewPager;
import app.codelabs.forum.R;

import app.codelabs.forum.activities.home.fragment.ArticleFragment;
import app.codelabs.forum.activities.home.fragment.adapter.TabLayoutAdapter;
import app.codelabs.forum.activities.shop.Adapter.AdapterShop;
import app.codelabs.forum.activities.shop.Adapter.AdapterTabLayoutShop;
import app.codelabs.forum.models.ResponsListShopByCategories;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentRincian extends Fragment {
    Context context;
    private TextView tvProductName, tvLocation, tvPrice,tvCategories;
    private ImageView ivImage;
    private TabLayout tabLayout;
    private ResponsListShopByCategories.DataEntity data;
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
        tvPrice.setText(Html.fromHtml(String.valueOf(data.getPrice())));
        tvCategories.setText(Html.fromHtml((data.getCategory())));
        Picasso.with(context).load(data.getPhoto()).fit().centerCrop().into(ivImage);
    }

    private void getData() {
        if (this.getArguments().getString("data") !=null){
            String strData = this.getArguments().getString("data");
            data = new Gson().fromJson(strData, ResponsListShopByCategories.DataEntity.class);
        }

    }


    private void setView(View view) {
        tvLocation = view.findViewById(R.id.tvlocation);
        tvPrice = view.findViewById(R.id.tvPrice);
        tvCategories = view.findViewById(R.id.tvCategory);
        tvProductName = view.findViewById(R.id.tvproductName);
        ivImage = view.findViewById(R.id.ivimage);
        viewPagerShop = view.findViewById(R.id.viewpagerrincian);
        tabLayout = view.findViewById(R.id.tab_layoutrincian);
    }

    private void setViewPager(){
        AdapterShop adapterShop = new AdapterShop(getChildFragmentManager());
        adapterShop.addFragment(new FragmentProDescription().setTypeAndData(FragmentProDescription.DESKRIP, new Gson().toJson(data)), "Description");
        adapterShop.addFragment(new FragmentProDescription().setTypeAndData(FragmentProDescription.PRODUCT_INFO,new Gson().toJson(data)), "Product Info");
        viewPagerShop.setAdapter(adapterShop);

        tabLayout.setupWithViewPager(viewPagerShop);


    }

}
