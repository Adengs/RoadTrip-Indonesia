package app.codelabs.roadtrip.activities.walktrough;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;

import java.util.List;

import app.codelabs.roadtrip.activities.login.LoginActivity;
import app.codelabs.roadtrip.helpers.Session;
import app.codelabs.roadtrip.models.ResponseWalkThrough;
import app.codelabs.roadtrip.R;
import app.codelabs.roadtrip.activities.walktrough.adapter.WalkThroughAdapter;
import app.codelabs.roadtrip.activities.walktrough.fragment.WalkTroughFragment;


public class WalkThroughActivity extends AppCompatActivity {
    private Context context;
    private List<ResponseWalkThrough.DataEntity> items;
    private WalkThroughAdapter pagerAdapter;

    public ViewPager viewPager;
    private DotsIndicator dotsIndicator;
    private TextView tvNext;
    private Button btnGettingStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_walk_though_activity);
        context = getApplicationContext();

        getPreviousData();
        setView();
        setEvent();
        setViewPager();
    }

    private void setEvent() {
        setEventViewPager();
        setEventBtnNext();
        setEventBtnGettingStart();
    }

    private void setEventBtnGettingStart() {
        btnGettingStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void setEventBtnNext() {
        tvNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
            }
        });
    }

    private void setEventViewPager() {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                boolean isLast = position == pagerAdapter.getCount() - 1;
                tvNext.setVisibility(isLast ? View.GONE : View.VISIBLE);
                btnGettingStart.setVisibility(isLast ? View.VISIBLE : View.GONE);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void getPreviousData() {
        items = Session.init(context).getWalkTrough();
    }

    private void setViewPager() {
        pagerAdapter = new WalkThroughAdapter(getSupportFragmentManager());
        for (ResponseWalkThrough.DataEntity item : items) {
            pagerAdapter.addfragment(new WalkTroughFragment().setData(item));
        }
        viewPager.setAdapter(pagerAdapter);
        dotsIndicator.setViewPager(viewPager);
    }

    private void setView() {
        viewPager = findViewById(R.id.viewpager);
        dotsIndicator = findViewById(R.id.pager_indicator);
        tvNext = findViewById(R.id.tv_next);
        btnGettingStart = findViewById(R.id.btn_get_started);
    }

}
