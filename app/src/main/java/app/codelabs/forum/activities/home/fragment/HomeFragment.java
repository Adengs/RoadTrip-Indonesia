package app.codelabs.forum.activities.home.fragment;


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

import app.codelabs.forum.R;
import app.codelabs.forum.activities.about_home.AboutHome;
import app.codelabs.forum.activities.article.ArticleActivity;
import app.codelabs.forum.activities.home.fragment.adapter.HomeCardSliderAdapter;
import app.codelabs.forum.activities.home.fragment.adapter.MenuAdapter;
import app.codelabs.forum.activities.home.fragment.adapter.TabLayoutAdapter;
import app.codelabs.forum.activities.home.notivication.NotivicationHome;
import app.codelabs.forum.activities.menu_event.MenuEventActivity;
import app.codelabs.forum.activities.gallery.GalleryActivity;
import app.codelabs.forum.activities.shop.ActivityProducts;
import app.codelabs.forum.activities.vote.VoteActivity;
import app.codelabs.forum.helpers.ConnectionApi;
import app.codelabs.forum.helpers.Session;
import app.codelabs.forum.models.HomeMenuItem;
import app.codelabs.forum.models.ResponseHighlight;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {
    private SliderView cardSlider;
    private ViewPager viewPager;
    private HomeCardSliderAdapter cardSliderAdapter;
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

        cardSliderAdapter = new HomeCardSliderAdapter(getContext());

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
                        cardSliderAdapter.setItems(response.body().getData());
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseHighlight> call, Throwable t) {
                if(t.getMessage() != null) {
                    Toast.makeText(context, t.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void setSliderView() {
        cardSlider.setSliderAdapter(cardSliderAdapter);
        cardSlider.startAutoCycle();
        cardSlider.setIndicatorAnimation(IndicatorAnimations.WORM);
        cardSlider.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
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
        menus.add(new HomeMenuItem(R.drawable.ic_events, "Events", MenuEventActivity.class)); // events
        menus.add(new HomeMenuItem(R.drawable.ic_shop, "Shop", ActivityProducts.class)); // shop
        menus.add(new HomeMenuItem(R.drawable.ic_gallery, "Gallery", GalleryActivity.class)); // gallery
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
