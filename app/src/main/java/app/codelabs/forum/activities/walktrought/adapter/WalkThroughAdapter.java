package app.codelabs.forum.activities.walktrought.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import app.codelabs.forum.models.ResponWalkThrough;

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
