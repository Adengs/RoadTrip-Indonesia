package app.codelabs.forum.activities.walktrough;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import app.codelabs.forum.R;
import app.codelabs.forum.activities.walktrough.adapter.WalkThroughAdapter;
import app.codelabs.forum.activities.walktrough.fragment.WalkTroughtFirstFragment;
import app.codelabs.forum.activities.walktrough.fragment.WalkTroughtSecondFragment;
import app.codelabs.forum.activities.walktrough.fragment.WalkTroughtThirdFragment;


import android.os.Bundle;


public class WalkThroughActivity extends AppCompatActivity {
    public ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_walk_though_activity);

        setView();
        setViewPager();

    }

    private void setViewPager() {
        WalkThroughAdapter adapter = new WalkThroughAdapter(getSupportFragmentManager());
        adapter.addfragment(new WalkTroughtFirstFragment());
        adapter.addfragment(new WalkTroughtSecondFragment());
        adapter.addfragment(new WalkTroughtThirdFragment());
        viewPager.setAdapter(adapter);
    }

    private void setView() {
        viewPager = findViewById(R.id.viewpager);
    }

}
