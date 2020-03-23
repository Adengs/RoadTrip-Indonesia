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

import com.google.android.material.tabs.TabLayout;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import app.codelabs.forum.R;
import app.codelabs.forum.activities.about_home.AboutHome;
import app.codelabs.forum.activities.article_home.ArticleHome;
import app.codelabs.forum.activities.home.fragment.adapter.HomeCardSliderAdapter;
import app.codelabs.forum.activities.home.fragment.adapter.MenuAdapter;
import app.codelabs.forum.activities.home.fragment.adapter.TabLayoutAdapter;
import app.codelabs.forum.activities.home.fragment.foryou.ForyouFragment;
import app.codelabs.forum.activities.home.fragment.latest.LatestFragment;
import app.codelabs.forum.activities.home.fragment.popular.PopularFragment;
import app.codelabs.forum.activities.home.notivication.NotivicationHome;
import app.codelabs.forum.activities.menu_event.MenuEventActivity;
import app.codelabs.forum.activities.menu_gallery.MenuGalleryActivity;
import app.codelabs.forum.activities.shop.ActivityShop;
import app.codelabs.forum.activities.vote.VoteActivity;
import app.codelabs.forum.models.HomeMenuItem;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    private SliderView cardSlider;
    private ViewPager viewPager;
    private HomeCardSliderAdapter cardSliderAdapter;
    private MenuAdapter menuAdapter;

    private ImageView img_bell;
    private TabLayout tabLayout;
    private RecyclerView recyclerView;

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

        cardSliderAdapter = new HomeCardSliderAdapter(getContext());

        menuAdapter = new MenuAdapter();

        setView(view);
        setEvent();
        setViewPager();
        setRecyclerView();
        setSliderView();

    }

    private void setSliderView() {
        cardSlider.setSliderAdapter(cardSliderAdapter);
        cardSlider.startAutoCycle();
        cardSlider.setIndicatorAnimation(IndicatorAnimations.WORM);
        cardSlider.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
    }

    private void setViewPager() {
        TabLayoutAdapter tabLayoutAdapter = new TabLayoutAdapter(getFragmentManager());

        tabLayoutAdapter.addFragment(new LatestFragment(), "Latest");
        tabLayoutAdapter.addFragment(new PopularFragment(), "Popular");
        tabLayoutAdapter.addFragment(new ForyouFragment(), "For You");
        viewPager.setAdapter(tabLayoutAdapter);

        tabLayout.setupWithViewPager(viewPager);

    }

    private void setRecyclerView() {
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        recyclerView.setAdapter(menuAdapter);

        List<HomeMenuItem> menus = new ArrayList<>();
        menus.add(new HomeMenuItem(R.drawable.ic_article, "Article", ArticleHome.class)); // article
        menus.add(new HomeMenuItem(R.drawable.ic_events, "Events", MenuEventActivity.class)); // events
        menus.add(new HomeMenuItem(R.drawable.ic_shop, "Shop", ActivityShop.class)); // shop
        menus.add(new HomeMenuItem(R.drawable.ic_gallery, "Gallery", MenuGalleryActivity.class)); // gallery
        menus.add(new HomeMenuItem(R.drawable.ic_about, "About", AboutHome.class)); // about
        menus.add(new HomeMenuItem(R.drawable.ic_vote, "Vote", VoteActivity.class)); // vote
        menuAdapter.setItems(menus);
    }

    private void setView(View view) {
        cardSlider = view.findViewById(R.id.imageSlider);
        recyclerView = view.findViewById(R.id.item_gridhome);
        tabLayout = view.findViewById(R.id.tab_layout_home);
        viewPager = view.findViewById(R.id.viewpager);
        img_bell = view.findViewById(R.id.iconbell);
    }

    private void setEvent() {
        img_bell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), NotivicationHome.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });

    }
}
