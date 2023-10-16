package app.codelabs.roadtrip.activities.home.fragment.home;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

import app.codelabs.roadtrip.R;
import app.codelabs.roadtrip.activities.about.AboutActivity;
import app.codelabs.roadtrip.activities.article.ArticleActivity;
import app.codelabs.roadtrip.activities.explore.ExploreActivity;
import app.codelabs.roadtrip.activities.home.adapter.HomeImageSliderAdapter;
import app.codelabs.roadtrip.activities.home.adapter.MenuAdapter;
import app.codelabs.roadtrip.activities.home.adapter.TabLayoutAdapter;
import app.codelabs.roadtrip.activities.article.fragment.ArticleFragment;
import app.codelabs.roadtrip.activities.notification.NotificationActivity;
import app.codelabs.roadtrip.activities.event.EventActivity;
import app.codelabs.roadtrip.activities.gallery.GalleryActivity;
import app.codelabs.roadtrip.activities.shop.ProductActivity;
import app.codelabs.roadtrip.helpers.ConnectionApi;
import app.codelabs.roadtrip.helpers.Session;
import app.codelabs.roadtrip.models.HomeMenuItem;
import app.codelabs.roadtrip.models.ResponseHighlight;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {
    private SliderView cardSlider;
    private ViewPager viewPager;
    private HomeImageSliderAdapter homeImageSliderAdapter;
    private MenuAdapter menuAdapter;
    private Session session;
    private Context context;
    private TextView img_bell;
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

        homeImageSliderAdapter = new HomeImageSliderAdapter(getContext());

        menuAdapter = new MenuAdapter();

        context = getContext();
        session = Session.init(context);

        setView(view);
        setEvent();
        setViewPager();
        setToolbar();
        setRecyclerView();
        setSliderView();
        loadHighlight();

    }

    private void setToolbar() {
    }

    private void loadHighlight() {
        ConnectionApi.apiService(context).getHighlight().enqueue(new Callback<ResponseHighlight>() {

            @Override
            public void onResponse(Call<ResponseHighlight> call, Response<ResponseHighlight> response) {
                if (response.body() != null) {
                    if (response.isSuccessful() && response.body().getSuccess()) {
                        homeImageSliderAdapter.setItems(response.body().getData());
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseHighlight> call, Throwable t) {
                if (t.getMessage() != null) {
                    Toast.makeText(context, t.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void setSliderView() {
        cardSlider.setSliderAdapter(homeImageSliderAdapter);
        cardSlider.startAutoCycle();
        cardSlider.setIndicatorAnimationDuration(600);
        cardSlider.setSliderAnimationDuration(600);
        cardSlider.setScrollTimeInSec(4);
        cardSlider.setIndicatorAnimation(IndicatorAnimations.FILL);
        cardSlider.setSliderTransformAnimation(SliderAnimations.FADETRANSFORMATION);
    }

    private void setViewPager() {
        TabLayoutAdapter tabLayoutAdapter = new TabLayoutAdapter(getChildFragmentManager());
        tabLayoutAdapter.addFragment(new ArticleFragment().setType(ArticleFragment.LATEST), "Latest");
        tabLayoutAdapter.addFragment(new ArticleFragment().setType(ArticleFragment.POPULAR), "Popular");
        viewPager.setAdapter(tabLayoutAdapter);

        tabLayout.setupWithViewPager(viewPager);

    }

    private void setRecyclerView() {
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        recyclerView.setAdapter(menuAdapter);

        List<HomeMenuItem> menus = new ArrayList<>();
        menus.add(new HomeMenuItem(R.drawable.ic_article, "Article", ArticleActivity.class)); // article
        menus.add(new HomeMenuItem(R.drawable.ic_events, "Events", EventActivity.class)); // events
        menus.add(new HomeMenuItem(R.drawable.ic_shop, "Shop", ProductActivity.class)); // shop
        menus.add(new HomeMenuItem(R.drawable.ic_gallery, "Gallery", GalleryActivity.class)); // gallery
        menus.add(new HomeMenuItem(R.drawable.ic_about, "About", AboutActivity.class)); // about
        menus.add(new HomeMenuItem(R.drawable.ic_explore, "Explore", ExploreActivity.class)); // explore
//        menus.add(new HomeMenuItem(R.drawable.ic_vote, "Vote", VoteActivity.class)); // vote
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
                Intent intent = new Intent(getContext(), NotificationActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });

    }
}
