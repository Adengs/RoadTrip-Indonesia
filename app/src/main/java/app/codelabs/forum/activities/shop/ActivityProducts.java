package app.codelabs.forum.activities.shop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import app.codelabs.forum.R;
import app.codelabs.forum.activities.event.schedule.ScheduleFragment;
import app.codelabs.forum.activities.event.walkietalkie.WalkieTalkieFragment;
import app.codelabs.forum.activities.home.HomeActivity;
import app.codelabs.forum.activities.home.fragment.ArticleFragment;
import app.codelabs.forum.activities.shop.Adapter.AdapterProductsAllFragment;
import app.codelabs.forum.activities.shop.Fragment.FragmentProductsAll;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.tabs.TabLayout;

public class ActivityProducts extends AppCompatActivity {
    Context context;
    TabLayout tabLayoutProducts;
    ViewPager viewPagerProducts;


    AdapterProductsAllFragment adapter;

    ImageView btnPro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        context = getApplicationContext();

        setView();
        setEvent();
        setViewPager();
    }




    private void setView() {
        tabLayoutProducts = findViewById(R.id.tab_layout);
        viewPagerProducts = findViewById(R.id.viewpager);

        btnPro = findViewById(R.id.btnbackpro);
    }

    private void setEvent() {

        btnPro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ActivityProducts.this, HomeActivity.class));
            }
        });
    }

    private void setViewPager() {

        adapter=new AdapterProductsAllFragment(getSupportFragmentManager());

        adapter.addFragment(new FragmentProductsAll(),"All");
        adapter.addFragment(new ArticleFragment(),"Cars");
        adapter.addFragment(new ScheduleFragment(),"Accessories");
        adapter.addFragment(new WalkieTalkieFragment(),"Parts");
        viewPagerProducts.setAdapter(adapter);

        tabLayoutProducts.setupWithViewPager(viewPagerProducts);
    }
}
