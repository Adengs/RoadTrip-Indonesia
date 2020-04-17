package app.codelabs.forum.activities.home.fragment.community;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import app.codelabs.forum.R;
import app.codelabs.forum.activities.home.adapter.PagerAdapter;
import app.codelabs.forum.activities.home.fragment.community.fragment.AboutFragment;
import app.codelabs.forum.activities.home.fragment.community.fragment.EventFragment;
import app.codelabs.forum.activities.home.fragment.community.fragment.GalleryFragment;
import app.codelabs.forum.activities.article.fragment.ArticleFragment;
import app.codelabs.forum.activities.home.fragment.community.fragment.MemberFragment;

public class CommunityFragment extends Fragment {

    private ViewPager viewPager;
    private PagerAdapter adapter;
    private TabLayout tabLayout;


    public CommunityFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_community, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setView(view);
        adapter = new PagerAdapter(getChildFragmentManager());
        viewPager.setAdapter(adapter);
        setViewPager();

    }


    private void setViewPager() {
        adapter = new PagerAdapter(getChildFragmentManager());
        adapter.addFragment(new ArticleFragment().setType(ArticleFragment.POST), "Post");
        adapter.addFragment(new MemberFragment(), "Member");
        adapter.addFragment(new EventFragment(), "Event");
        adapter.addFragment(new GalleryFragment(), "Gallery");
        adapter.addFragment(new AboutFragment(), "About");

        this.viewPager.setAdapter(adapter);

        tabLayout.setupWithViewPager(this.viewPager);
    }

    private void setView(View view) {
        viewPager = view.findViewById(R.id.view_pager_club);
        tabLayout = view.findViewById(R.id.tab_layout);
    }

}

