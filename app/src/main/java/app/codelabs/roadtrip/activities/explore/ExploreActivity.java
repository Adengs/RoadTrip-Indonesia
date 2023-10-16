package app.codelabs.roadtrip.activities.explore;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import app.codelabs.roadtrip.R;
import app.codelabs.roadtrip.activities.explore.adapter.CategoryExploreAdapter;
import app.codelabs.roadtrip.activities.explore.list.ListLocationFragment;
import app.codelabs.roadtrip.activities.explore.list.ListSearchActivity;
import app.codelabs.roadtrip.activities.explore.maps.ListMapsSearchActivity;
import app.codelabs.roadtrip.activities.explore.maps.MapsFragment;
import app.codelabs.roadtrip.activities.explore.maps.MapsSearchActivity;
import app.codelabs.roadtrip.activities.home.adapter.TabLayoutAdapter;
import app.codelabs.roadtrip.activities.home.fragment.profile.fragment.BookmarkFragment;
import app.codelabs.roadtrip.activities.home.fragment.profile.fragment.MyShopFragment;
import app.codelabs.roadtrip.activities.home.fragment.profile.fragment.ProfileUserFragment;
import app.codelabs.roadtrip.activities.shop.adapter.AdapterTabLayoutShop;
import app.codelabs.roadtrip.activities.shop.fragment.ListShopFragment;
import app.codelabs.roadtrip.helpers.ActivityUtils;
import app.codelabs.roadtrip.helpers.ConnectionApi;
import app.codelabs.roadtrip.helpers.RecentUtils;
import app.codelabs.roadtrip.helpers.StringUtil;
import app.codelabs.roadtrip.helpers.Validator;
import app.codelabs.roadtrip.models.CategoryExploreSelected;
import app.codelabs.roadtrip.models.EventBusClass;
import app.codelabs.roadtrip.models.ResponseCategoryExplore;
import app.codelabs.roadtrip.models.ResponseListShopByCategories;
import app.codelabs.roadtrip.models.ResponseShopCategory;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExploreActivity extends AppCompatActivity {
    private Context context;
//    private EditText etSearch;
    private ImageView btnSearch;
    private ImageView ivBack;
    private ProgressBar progressBar;
//    private TabLayout tabLayout;
    private TabLayout tabLayoutBottom;
    private TabLayoutAdapter tabLayoutAdapter;
    private ViewPager viewPagerProducts;
    private RecyclerView recyclerView;
    private CategoryExploreAdapter adapterCategory;
    private int id = 1;
    private int tabPos = 0;
    MainAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore);
        context = getApplicationContext();

        getExploreByCategory();
        setView();
        setEvent();
        setRecyclerView();
//        setTab();
        getCategories();

        tabLayoutBottom.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tabPos = tab.getPosition();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

//    private void setTab() {
//        adapter = new MainAdapter(getSupportFragmentManager());
//
//        adapter.AddFragment(new ListLocationFragment(), "Toko & Bengkel");
//        adapter.AddFragment(new ListLocationFragment(), "Wisata");
//        adapter.AddFragment(new ListLocationFragment(), "Resto");
//        adapter.AddFragment(new ListLocationFragment(), "Rental");
//
//        viewPagerProducts.setAdapter(adapter);
//        tabLayout.setupWithViewPager(viewPagerProducts);
//    }

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
//        tabLayout.setVisibility(View.VISIBLE);
        viewPagerProducts.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
        tabLayoutBottom.setVisibility(View.VISIBLE);

        AdapterTabLayoutShop adapterTabLayoutShop = new AdapterTabLayoutShop(getSupportFragmentManager());

        for (ResponseShopCategory.DataEntity item : data) {
            adapterTabLayoutShop.addFragment(
                    new ListShopFragment()
                            .setTypeAndReferenceId(ListShopFragment.CATEGORIES,
                                    item.getId()), StringUtil.toCapitalize(item.getCategory()));
        }

        tabLayoutAdapter = new TabLayoutAdapter(getSupportFragmentManager());
        tabLayoutAdapter.addFragment(new ListLocationFragment(), "List");
        tabLayoutAdapter.addFragment(new MapsFragment(), "Maps");


        viewPagerProducts.setAdapter(tabLayoutAdapter);
//        viewPagerProducts.setAdapter(adapterTabLayoutShop);
//        tabLayout.setupWithViewPager(viewPagerProducts);
        tabLayoutBottom.setupWithViewPager(viewPagerProducts);


//                .addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//                tabLayoutBottom.getTabAt(position).select();
//                Log.e("TAG",  String.valueOf(position));
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        });

    }

    private void getExploreByCategory() {
//        if (getArguments() != null) {
//            int referenceId = getArguments().getInt("reference_id", 0);
            ConnectionApi.apiService(context).getExploreByCategories().enqueue(new Callback<ResponseCategoryExplore>() {
                @Override
                public void onResponse(Call<ResponseCategoryExplore> call, Response<ResponseCategoryExplore> response) {
                    if (response.body() != null) {
                        if (response.isSuccessful() & response.body().getSuccess()) {
//                            Log.e("Category", "onResponse: " + response.body().getData().size() );
                            setListCategory(response.body().getData());
                        }
                    }
                }

                @Override
                public void onFailure(Call<ResponseCategoryExplore> call, Throwable t) {
                    if (t.getMessage() != null) {
                        Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
//        }
    }

    private void setListCategory(List<ResponseCategoryExplore.DataEntity> data) {
        adapterCategory.setItems(data);
    }

    private void setRecyclerView(){
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(adapterCategory);

        if (0 == recyclerView.getItemDecorationCount()){
            recyclerView.addItemDecoration(new RecentUtils.PaddingItemDecoration(30));
        }
    }

    private void setView() {
//        tabLayout = findViewById(R.id.tab_layout);
        tabLayoutBottom = findViewById(R.id.tab_layout_bottom);
        viewPagerProducts = findViewById(R.id.viewpager);
        ivBack = findViewById(R.id.iv_back);
//        etSearch = findViewById(R.id.et_search);
        btnSearch = findViewById(R.id.btn_search);
        progressBar = findViewById(R.id.progressbar);

        recyclerView = findViewById(R.id.rv_tab_category);
        adapterCategory = new CategoryExploreAdapter();

    }

    private void setEvent() {
//        setEventSearch();
        setEventBtnBack();
        setEventSearch();
    }

    private void setEventSearch() {
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("id", "onClick: " + id );
                Log.e("tabPos", "onClick: " + tabPos );
                if (tabPos == 0){
                    Intent intent = new Intent(context, ListSearchActivity.class);
                    intent.putExtra("id-list", id);
                    startActivity(intent);
                }
                if (tabPos == 1){
                    Intent intent = new Intent(context, ListMapsSearchActivity.class);
                    intent.putExtra("id-maps", id);
                    startActivity(intent);
                }
//                if (tabPos == 1){
//                    Intent intent = new Intent(context, MapsSearchActivity.class);
//                    intent.putExtra("id-maps", id);
//                    startActivity(intent);
//                }
            }
        });
    }

//    private void setEventSearch() {
//        etSearch.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                EventBus.getDefault().post(new EventBusClass.SearchShop(s.toString()));
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });
//        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
//            @Override
//            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
//                if (actionId == EditorInfo.IME_ACTION_SEARCH || actionId == EditorInfo.IME_ACTION_DONE) {
//                    search();
//                    return true;
//                }
//
//                return false;
//            }
//        });
//        etSearch.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                view.performClick();
//                final int DRAWABLE_LEFT = 0;
//                final int DRAWABLE_TOP = 1;
//                final int DRAWABLE_RIGHT = 2;
//                final int DRAWABLE_BOTTOM = 3;
//
//                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
//                    if (motionEvent.getRawX() >= (etSearch.getRight() - etSearch.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
//                        // your action here
//                        search();
//                        return true;
//                    }
//                }
//                return false;
//            }
//        });
//    }

//    private void search() {
//        if (Validator.isFieldEmpty(etSearch)) {
//            etSearch.clearFocus();
//        }
//        EventBus.getDefault().post(new EventBusClass.SearchShop(etSearch.getText().toString()));
//        ActivityUtils.hideKeyboard(this, etSearch);
//
//    }

    private void setEventBtnBack() {
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }


//    @Override
//    public void onTabSelected(TabLayout.Tab tab) {
//        final int position = tab.getPosition();
//        Log.e("card", "Tablayout pos: " + position);
//    }
//
//    @Override
//    public void onTabUnselected(TabLayout.Tab tab) {
//
//    }
//
//    @Override
//    public void onTabReselected(TabLayout.Tab tab) {
//
//    }

    private class MainAdapter extends FragmentPagerAdapter {
        ArrayList<Fragment> fragmentArrayList = new ArrayList<>();
        ArrayList<String> stringArrayList = new ArrayList<>();

        public void AddFragment(Fragment fragment, String s){
            fragmentArrayList.add(fragment);
            stringArrayList.add(s);
        }

        public MainAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragmentArrayList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentArrayList.size();
        }

        @NonNull
        @Override
        public CharSequence getPageTitle(int position){
            return stringArrayList.get(position);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSelectCategory(CategoryExploreSelected categoryExploreSelected){
        id = categoryExploreSelected.id;
    }
}