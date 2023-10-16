package app.codelabs.roadtrip.activities.home.fragment.profile.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;

import app.codelabs.roadtrip.R;
import app.codelabs.roadtrip.activities.home.adapter.TabLayoutAdapter;

public class BookmarkFragment extends Fragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Context context;
    private TabLayoutAdapter tabLayoutAdapter;

    public BookmarkFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_bookmark, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        context = getContext();
        setView(view);
        setTabAndPager();
    }

    private void setView(View view) {
        tabLayout = view.findViewById(R.id.tab_layout);
        viewPager = view.findViewById(R.id.viewpager);
    }

    private void setTabAndPager() {
        tabLayoutAdapter = new TabLayoutAdapter(getChildFragmentManager());
        tabLayoutAdapter.addFragment(new BookmarkArticleFragment(), "Article");
        tabLayoutAdapter.addFragment(new BookmarkEventFragment(), "Event");
        tabLayoutAdapter.addFragment(new BookmarkShopFragment(), "Shop");
        viewPager.setAdapter(tabLayoutAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

}
