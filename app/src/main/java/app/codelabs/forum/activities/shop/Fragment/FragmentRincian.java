package app.codelabs.forum.activities.shop.Fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import androidx.viewpager.widget.ViewPager;
import app.codelabs.forum.R;

import app.codelabs.forum.activities.shop.Adapter.AdapterShop;
import app.codelabs.forum.activities.shop.Adapter.AdapterTabLayoutShop;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentRincian extends Fragment {
    Context context;
    private TabLayout tabLayout;
    private  AdapterTabLayoutShop adapterTabLayoutShop;
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


        setView(view);

        setViewPager();

    }




    private void setView(View view) {

        viewPagerShop = view.findViewById(R.id.viewpagerrincian);
        tabLayout = view.findViewById(R.id.tab_layoutrincian);
    }

    private void setViewPager() {


        adapterTabLayoutShop = new AdapterTabLayoutShop(getFragmentManager());
        adapterTabLayoutShop.addFragment(new FragmentProDescription(),"Description");
        adapterTabLayoutShop.addFragment(new FragmentProInfo(),"Product Info");

        viewPagerShop.setAdapter(adapterTabLayoutShop);

        tabLayout.setupWithViewPager(viewPagerShop);


    }




}
