package app.codelabs.forum.activities.home.fragment;


import android.content.Intent;
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

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import app.codelabs.forum.R;
import app.codelabs.forum.activities.home.fragment.adapter.HomeCardSliderAdapter;
import app.codelabs.forum.activities.home.fragment.adapter.MenuAdapter;
import app.codelabs.forum.activities.home.fragment.adapter.TabLayoutAdapter;
import app.codelabs.forum.activities.home.fragment.foryou.ForyouFragment;
import app.codelabs.forum.activities.home.fragment.latest.LatestFragment;
import app.codelabs.forum.activities.home.fragment.popular.PopularFragment;
import app.codelabs.forum.activities.home.notivication.NotivicationHome;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
private CardSliderViewPager cardSliderViewPager;
    ViewPager viewPager;
    private HomeCardSliderAdapter adapter;
    MenuAdapter menuAdapter;
    TabLayoutAdapter tabLayoutAdapter;
    TabLayout tabLayout;
    RecyclerView recyclerView;

    ArrayList<Fragment> items = new ArrayList<Fragment>();

    TextView txtexplorer,txtfevci;;
    ImageView img_bell;

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

        menuAdapter = new MenuAdapter();




        setView(view);
        setEvent();
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
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),1));
        recyclerView.setAdapter(menuAdapter);


    }
    private void setView(View view) {
        cardSliderViewPager = view.findViewById(R.id.viewpager);
        recyclerView = view.findViewById(R.id.item_gridhome);
        tabLayout = view.findViewById(R.id.tab_layouthome);
        viewPager= view.findViewById(R.id.viewpagers);
        txtexplorer = view.findViewById(R.id.txtexplorer);
        txtfevci = view.findViewById(R.id.txtfevci);
        img_bell = view.findViewById(R.id.iconbell);
    }
    private void setEvent() {
        img_bell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), NotivicationHome.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });

    }
}
