package app.codelabs.forum.activities.shop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;
import app.codelabs.forum.R;
import app.codelabs.forum.activities.shop.Adapter.AdapterShop;
import app.codelabs.forum.activities.shop.Fragment.FragmentProDescription;
import app.codelabs.forum.activities.shop.Fragment.FragmentProInfo;
import app.codelabs.forum.activities.shop.Fragment.FragmentRincian;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.tabs.TabLayout;

public class ActivityShop extends AppCompatActivity {
    Context context;
    ImageView btnbacks;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        context = getApplicationContext();

        setView();
        setEvent();
        setFragment(new FragmentRincian());
    }

    private void setFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_containershop, fragment);
        fragmentTransaction.commit();
    }

    private void setEvent() {
        btnbacks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ActivityShop.this, ActivityProducts.class));
            }
        });

    }

    private void setView() {
        btnbacks = findViewById(R.id.btnbackShop);

    }
}
