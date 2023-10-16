package app.codelabs.roadtrip.activities.home.adapter;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class TabLayoutAdapter extends FragmentPagerAdapter {
    List<Fragment> items;
    List<String> titles;

    public TabLayoutAdapter(FragmentManager fm) {
        super(fm);
        items = new ArrayList<>();
        titles = new ArrayList<>();
    }

    @Override
    public Fragment getItem(int position) {
        return items.get(position);
    }

    @Override
    public int getCount() {
        return items.size();
    }

    public void addFragment(Fragment fragment, String title) {
        items.add(fragment);
        titles.add(title);
    }

    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}
