package app.codelabs.forum.activities.shop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import app.codelabs.forum.R;
import app.codelabs.forum.activities.event.adapter.AdapterEventActivity2;
import app.codelabs.forum.activities.event.chat.ChatFragment;
import app.codelabs.forum.activities.event.description.DescriptionFragment;
import app.codelabs.forum.activities.event.participant.ParticipantFragment;
import app.codelabs.forum.activities.event.schedule.ScheduleFragment;
import app.codelabs.forum.activities.event.walkietalkie.WalkieTalkieFragment;
import app.codelabs.forum.activities.home.HomeActivity;
import app.codelabs.forum.activities.home.fragment.latest.LatestFragment;
import app.codelabs.forum.activities.shop.Adapter.AdapterShopProducts;
import app.codelabs.forum.activities.shop.Fragment.FragmentShopProducts;

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


    AdapterShopProducts adapter;

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
        tabLayoutProducts = findViewById(R.id.tab_layoutshop);
        viewPagerProducts = findViewById(R.id.viewpagershop);

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

        adapter=new AdapterShopProducts(getSupportFragmentManager());

        adapter.addFragment(new FragmentShopProducts(),"All");
        adapter.addFragment(new LatestFragment(),"Cars");
        adapter.addFragment(new ScheduleFragment(),"Accessories");
        adapter.addFragment(new WalkieTalkieFragment(),"Parts");
        viewPagerProducts.setAdapter(adapter);

        tabLayoutProducts.setupWithViewPager(viewPagerProducts);
    }
}
