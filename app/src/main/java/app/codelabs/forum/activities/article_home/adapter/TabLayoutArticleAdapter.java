package app.codelabs.forum.activities.article_home.adapter;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class TabLayoutArticleAdapter extends FragmentPagerAdapter {
    List<Fragment> items = new ArrayList<>();
    List<String> titles = new ArrayList<>();
    public TabLayoutArticleAdapter(FragmentManager fm) {
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
    public void addFragment(Fragment fragment, String title){
        items.add(fragment);
        titles.add(title);
        notifyDataSetChanged();
    }
    public CharSequence getPageTitle(int position){
        return titles.get(position);
    }
}
