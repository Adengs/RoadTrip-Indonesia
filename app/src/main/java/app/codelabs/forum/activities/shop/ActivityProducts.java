package app.codelabs.forum.activities.shop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;
import app.codelabs.forum.R;
import app.codelabs.forum.activities.shop.Adapter.AdapterTabLayoutShop;
import app.codelabs.forum.activities.shop.Fragment.ListShopFragment;
import app.codelabs.forum.helpers.ConnectionApi;
import app.codelabs.forum.models.ResponseShopCategory;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;


import com.google.android.material.tabs.TabLayout;

import java.util.List;

public class ActivityProducts extends AppCompatActivity {
    private Context context;
    private EditText etSearch;
    private ProgressBar progressBar;
    private TabLayout tabLayout;
    private ViewPager viewPagerProducts;
    private Toolbar toolbar;
    private AdapterTabLayoutShop adapterTabLayoutShop;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        context = getApplicationContext();

        setView();
        setEvent();
        setToolbar();
        getCategories();
    }

    private void getCategories() {
        ConnectionApi.apiService(context).getShopCategories().enqueue(new Callback<ResponseShopCategory>() {
            @Override
            public void onResponse(Call<ResponseShopCategory> call, Response<ResponseShopCategory> response) {
                if (response.body() != null) {
                    if (response.isSuccessful() && response.body().getSuccess()) {
                        setTabLayout(response.body().getData());
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseShopCategory> call, Throwable t) {
                if (t.getMessage() != null) {
                    Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setTabLayout(List<ResponseShopCategory.DataEntity> data) {
        tabLayout.setVisibility(View.VISIBLE);
        viewPagerProducts.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);

        AdapterTabLayoutShop adapterTabLayoutShop = new AdapterTabLayoutShop(getSupportFragmentManager());

        for (ResponseShopCategory.DataEntity item : data){
            adapterTabLayoutShop.addFragment(new ListShopFragment().setTypeAndReferenceId(ListShopFragment.CATEGORIES,
                    item.getId()),item.getCategory());
        }
        viewPagerProducts.setAdapter(adapterTabLayoutShop);
        tabLayout.setupWithViewPager(viewPagerProducts);


    }

    private void setToolbar() {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        setTitle("Product");
    }



    private void setView() {
        tabLayout = findViewById(R.id.tab_layout);
        viewPagerProducts = findViewById(R.id.viewpager);
        toolbar = findViewById(R.id.toolbar);
        etSearch = findViewById(R.id.et_Search);
        progressBar = findViewById(R.id.progressbar);

    }

    private void setEvent() {
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
