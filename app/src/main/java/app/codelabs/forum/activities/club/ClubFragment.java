package app.codelabs.forum.activities.club;


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
import app.codelabs.forum.activities.club.about.AboutFragment;
import app.codelabs.forum.activities.club.event_club.Event_Club_Fragment;
import app.codelabs.forum.activities.gallery.fragment.GalleryFragment;
import app.codelabs.forum.activities.club.member.MemberFragment;
import app.codelabs.forum.activities.club.post.PostFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class ClubFragment extends Fragment {

    private ViewPager viewPager;
    private PagerAdapter adapter;
    private TabLayout tabLayout;


    public ClubFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_club, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setView(view);
        adapter = new PagerAdapter(getFragmentManager());
        adapter.addFragment(new PostFragment(), "Post");
        viewPager.setAdapter(adapter);
        setViewPager();

    }


    private void setViewPager() {
        adapter = new PagerAdapter(getChildFragmentManager());
        adapter.addFragment(new PostFragment(), "Post");
        adapter.addFragment(new MemberFragment(), "Member");
        adapter.addFragment(new Event_Club_Fragment(), "Event");
        adapter.addFragment(new GalleryFragment(), "Gallery");
        adapter.addFragment(new AboutFragment(), "About");

        this.viewPager.setAdapter(adapter);

        tabLayout.setupWithViewPager(this.viewPager);
    }

    private void setView(View view) {
        viewPager = view.findViewById(R.id.viewPagerClub);
        tabLayout = view.findViewById(R.id.tab_layout);
    }

}

