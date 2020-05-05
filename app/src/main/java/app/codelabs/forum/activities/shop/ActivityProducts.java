package app.codelabs.forum.activities.shop;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;
import app.codelabs.forum.R;
import app.codelabs.forum.activities.shop.adapter.AdapterTabLayoutShop;
import app.codelabs.forum.activities.shop.fragment.ListShopFragment;
import app.codelabs.forum.helpers.ConnectionApi;
import app.codelabs.forum.helpers.StringUtil;
import app.codelabs.forum.models.EventBusClass;
import app.codelabs.forum.models.ResponseShopCategory;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityProducts extends AppCompatActivity {
    private Context context;
    private EditText etSearch;
    private ProgressBar progressBar;
    private TabLayout tabLayout;
    private ViewPager viewPagerProducts;
    private Toolbar toolbar;
    private EventBusClass.Search eventBusClassSearch = new EventBusClass.Search();
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

        for (ResponseShopCategory.DataEntity item : data) {
            adapterTabLayoutShop.addFragment(
                    new ListShopFragment()
                            .setTypeAndReferenceId(ListShopFragment.CATEGORIES,
                                    item.getId()), StringUtil.toCapitalize(item.getCategory()));
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
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                eventBusClassSearch.setSearch(String.valueOf(s));
                EventBus.getDefault().post(eventBusClassSearch);
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
