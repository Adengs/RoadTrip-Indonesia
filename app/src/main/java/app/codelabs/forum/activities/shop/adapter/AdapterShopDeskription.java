package app.codelabs.forum.activities.shop.adapter;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class AdapterShopDeskription extends FragmentPagerAdapter {


    List<Fragment> items;
    List<String> titles;

    public AdapterShopDeskription(FragmentManager fm) {
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

    public void addFragment(Fragment fragment ,String title){
        items.add(fragment);
        titles.add(title);
        notifyDataSetChanged();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}
