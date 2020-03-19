package app.codelabs.forum.activities.walktrought;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;
import app.codelabs.forum.R;
import app.codelabs.forum.activities.splashscreen.SplashScreenActivity;
import app.codelabs.forum.activities.walktrought.adapter.WalkThroughAdapter;
import app.codelabs.forum.activities.walktrought.fragment.WalkTroughtFirstFragment;
import app.codelabs.forum.activities.walktrought.fragment.WalkTroughtSecondFragment;
import app.codelabs.forum.activities.walktrought.fragment.WalkTroughtThirdFragment;
import app.codelabs.forum.models.ResponWalkThrough;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.List;

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
