package app.codelabs.roadtrip.activities.walktrough.adapter;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class WalkThroughAdapter extends FragmentPagerAdapter {
    List<Fragment> items = new ArrayList<>();
    public WalkThroughAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return items.get(position);
    }

    @Override
    public int getCount() {
        return items.size();
    }
    public void addfragment(Fragment fragment){
        items.add(fragment);
    }
}
