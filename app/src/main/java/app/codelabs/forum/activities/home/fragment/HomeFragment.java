package app.codelabs.forum.activities.home.fragment;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.islamkhsh.CardSliderViewPager;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import app.codelabs.forum.R;
import app.codelabs.forum.activities.home.fragment.adapter.HomeAdapter;
import app.codelabs.forum.activities.home.fragment.adapter.HomeCardSliderAdapter;
import app.codelabs.forum.activities.home.fragment.adapter.TabLayoutAdapter;
import app.codelabs.forum.activities.home.fragment.foryou.ForyouFragment;
import app.codelabs.forum.activities.home.fragment.latest.LatestFragment;
import app.codelabs.forum.activities.home.fragment.popular.PopularFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
private CardSliderViewPager cardSliderViewPager;
    ViewPager viewPager;
    private HomeCardSliderAdapter adapter;

    TabLayoutAdapter tabLayoutAdapter;
    TabLayout tabLayout;
    HomeAdapter homeAdapter;
    RecyclerView recyclerView;
    Context context;

    ArrayList<Fragment> items = new ArrayList<Fragment>();


    TextView txtexplorer;
    ImageView imgArtikel,imgEvent,imgShop,imgGalery,imgAbout,imgVote;
    TextView txtArtikel,txtEvent,txtShop,txtGalery,txtAbout,txtVote;



    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        adapter = new HomeCardSliderAdapter(items);
        homeAdapter = new HomeAdapter();



        setView(view);
        //setEvent();
        setViewPager();
        setRecyclerView();

    }

    private void setViewPager() {
        tabLayoutAdapter= new TabLayoutAdapter(getFragmentManager());

        tabLayoutAdapter.addFragment(new LatestFragment(),"Latest");
        tabLayoutAdapter.addFragment(new PopularFragment(),"Popular");
        tabLayoutAdapter.addFragment(new ForyouFragment(),"For You");
        viewPager.setAdapter(tabLayoutAdapter);

        tabLayout.setupWithViewPager(viewPager);

    }

    private void setRecyclerView() {
        cardSliderViewPager.setAdapter(adapter);
        recyclerView.setAdapter(homeAdapter);
    }

    private void setView(View view) {
        cardSliderViewPager = view.findViewById(R.id.viewpager);
        recyclerView = view.findViewById(R.id.viewtablayout);
        tabLayout = view.findViewById(R.id.tab_layouthome);
        viewPager= view.findViewById(R.id.viewpagers);
        txtexplorer = view.findViewById(R.id.txtexplorer);
        imgArtikel = view.findViewById(R.id.img_artikel);
        txtArtikel = view.findViewById(R.id.txtArtikel);
        imgEvent = view.findViewById(R.id.img_event);
        txtEvent = view.findViewById(R.id.txtEvent);
        imgShop = view.findViewById(R.id.img_shop);
        txtShop = view.findViewById(R.id.txtShop);
        imgGalery = view.findViewById(R.id.img_galeri);
        txtGalery = view.findViewById(R.id.txtGalery);
        imgAbout = view.findViewById(R.id.img_about);
        txtAbout = view.findViewById(R.id.txtAbout);
        imgVote = view.findViewById(R.id.img_vote);
        txtVote = view.findViewById(R.id.txtVote);
    }
    private void setEvent() {

    }
}
